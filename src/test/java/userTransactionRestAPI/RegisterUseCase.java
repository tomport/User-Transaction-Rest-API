package userTransactionRestAPI;

import org.springframework.stereotype.Service;
import userTransactionRestAPI.user.User;
import userTransactionRestAPI.user.UserRepository;


@Service
public class RegisterUseCase {

    private final UserRepository userRepository;

    public RegisterUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
