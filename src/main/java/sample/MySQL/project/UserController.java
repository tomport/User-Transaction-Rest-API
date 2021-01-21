package sample.MySQL.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    private User addNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    @DeleteMapping("/users/{userId}")
    private String deleteUser(@PathVariable("userId") Long userId)
    {
        userRepository.deleteById(userId);
        return "Deleted";
    }
    @GetMapping("/users")
    private List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    @GetMapping("/users/{userId}")
    private User getUser(@PathVariable("userId") Long userId)
    {
        return userRepository.findById(userId).get();
    }
    @PutMapping("/users/{userId}")
    private User updateUser(@RequestBody User user, @PathVariable("userId") Long userId)
    {
        return userRepository.findById(userId)
                .map(user1 -> {
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    return userRepository.save(user1);
                })
                .orElseGet(() -> {
                    user.setUserId(userId);
                    return userRepository.save(user);
                });
    }
    @GetMapping("/users/byLastName/{lastName}")
    private List<User> getUsersByLastName(@PathVariable("lastName") String lastName) {
        return userRepository.findByLastName(lastName);
    }
}
