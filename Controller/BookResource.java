package Controller;

import com.example.libraryManagementSpring.DataAccessLayer.Book;
import com.example.libraryManagementSpring.DataAccessLayer.BookRepo;
import com.example.libraryManagementSpring.DataAccessLayer.IssuedBookRepo;
import com.example.libraryManagementSpring.DataAccessLayer.UserRepo;
import com.example.libraryManagementSpring.Util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class BookResource {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    BookRepo bookRepo;

    @Autowired
    IssuedBookRepo issuedBookRepo;

    @Autowired
    UserRepo userRepo;

    //status 200
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/books")
    List<Book> getallbooks()  {
        LOGGER.info("FindAll is called");
        List<Book> ls =bookRepo.findAll();
        if(ls.isEmpty())
        {
            try {
                LOGGER.severe("No Book Found");
                throw new Exception("BookNotFound");
            }
            catch (Exception exc)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"NotFound",exc);
            }
        }
        else
        {       return ls;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/book")
    Book addNewBook(@RequestBody Book book) throws Exception {
        LOGGER.info("Save will be Called");
        if(BookValidator.isValid(book))
        {
            LOGGER.info("BookAddedSuccesfully");
         return   bookRepo.save(book);

        }
        else
        {
            LOGGER.severe("NotValidBook");
            return  null;
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/searchbookbyauthor")
    List<Book> searchByAuthor(@RequestParam(value = "q")String author)
    {
        List<Book>res=new ArrayList<>();
        List<Book>ls=bookRepo.findAll();
        for(Book x:ls)
        {
            if(x.getAuthor().equals(author))
                res.add(x);
        }
        return res;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/searchbookbysubject")
    List<Book> searchBySubject(@RequestParam(value = "q")String subject)
    {
        List<Book>res=new ArrayList<>();
        List<Book>ls=bookRepo.findAll();
        for(Book x:ls)
        {
            if(x.getSubject().equals(subject))
                res.add(x);
        }
        return res;
    }
}
