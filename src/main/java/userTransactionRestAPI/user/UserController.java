package userTransactionRestAPI.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userTransactionRestAPI.userTransaction.UserTransactionService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserTransactionService userTransactionService;

    @PostMapping("/users")
    private User addUser(@RequestBody User user) {
        return userTransactionService.addNewUser(user);
    }
    @DeleteMapping("/users/{userId}")
    private String deleteUser(@PathVariable("userId") Long userId) {
        return userTransactionService.removeUser(userId);
    }
    @GetMapping("/users")
    private List<User> getAllUsers() {
        return userTransactionService.getUsers();
    }
    @GetMapping("/users/{userId}")
    private User getById(@PathVariable("userId") Long userId) {
        return userTransactionService.getUserById(userId);
    }

    @GetMapping("/users/byLastName/{lastName}")
    private List<User> getByLastName(@PathVariable("lastName") String lastName) {
        return userTransactionService.getUserByLastName(lastName);
    }

    @PutMapping("/users/{userId}")
    private User updateUser(@RequestBody User user, @PathVariable("userId") Long userId) {
        return userTransactionService.updateUserById(user, userId);
    }

}
