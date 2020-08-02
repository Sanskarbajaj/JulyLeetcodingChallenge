package Controller;

import com.example.libraryManagementSpring.DataAccessLayer.IssuedBookRepo;
import com.example.libraryManagementSpring.DataAccessLayer.IssuedBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class IssuedBookResource {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    IssuedBookRepo issuedBookRepo;

    @GetMapping("/issuedBooks")
    List<IssuedBooks> findIssuedBooks() {
        List<IssuedBooks> ls = issuedBookRepo.findAll();
        {
            LOGGER.info("ISSUED BOOK FINDALL CALLED");
            if (ls.isEmpty()) {
            try {
        throw new Exception("NO BOOK FOUND");
            }
            catch (Exception e)
            {
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"NotFound",e);
            }
            } else {
                return ls;
            }
        }
    }

    @PostMapping(value = "/issueBook")
    @ResponseStatus(HttpStatus.CREATED)
    public IssuedBooks issueBook(@RequestBody IssuedBooks issuedBooks) throws Exception{
        try {
            return issuedBookRepo.save(issuedBooks);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
    @GetMapping(value = "/searchIssuedBooksByUser")
    public List<IssuedBooks> searchIssuedBooksByUser(@RequestParam(value = "q") String userId){
        LOGGER.info("searchIssuedBooksByUser called with userId "+userId);
        List<IssuedBooks> books = issuedBookRepo.findAll();
        ArrayList<IssuedBooks> list = new ArrayList<IssuedBooks>();
        for(IssuedBooks book : books){
            int id = Integer.parseInt(userId);
            if(book.getUser_id()==id){
                list.add(book);
            }
        }
        return list;
    }
}
