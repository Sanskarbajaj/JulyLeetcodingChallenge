package Controller;
import com.example.libraryManagementSpring.DataAccessLayer.User;
import com.example.libraryManagementSpring.DataAccessLayer.UserRepo;
import com.example.libraryManagementSpring.Util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class UserResource {
    @Autowired
    UserRepo userRepo;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @GetMapping("/users")
    List<User> findAll() {
        return userRepo.findAll();
    }

    @PostMapping("/users")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    User newUser(@RequestBody User newUser) throws Exception {
// @ , ., com
        if(UserValidator.isValidUser(newUser))
            return userRepo.save(newUser);
        else throw new Exception();
    }

    // Find a given user
    @GetMapping("/users/{id}")
    User findOne(@PathVariable int id) {
        LOGGER.info("/users/{id} called with id "+ id);
        Optional<User> user = userRepo.findById(id);
        return user.get();

        //return repository.findById(id)
          //      .orElseThrow(() -> new UserNotFoundException(id));
    }
}
