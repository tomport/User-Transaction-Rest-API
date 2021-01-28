package userTransactionRestAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userTransactionRestAPI.user.User;
import userTransactionRestAPI.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterUseCaseTest {

    private UserRepository userRepository;

    private RegisterUseCase registerUseCase;

    @BeforeEach
    void initUseCase() {
        registerUseCase = new RegisterUseCase(userRepository);
    }

    @Test
    void savedUserHasRegistrationDate() {
        User user = new User("Frodo", "Baggins");
        User savedUser = registerUseCase.registerUser(user);
        assertThat(savedUser.getFirstName()).isEqualTo("Frodo");
        assertThat(savedUser.getFirstName()).isEqualTo("Baggins");
    }

}

