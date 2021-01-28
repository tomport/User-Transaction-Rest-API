package userTransactionRestAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import userTransactionRestAPI.user.User;
import userTransactionRestAPI.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class RegisterUseCaseTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private RegisterUseCase registerUseCase;

    @Test
    void savedUserHasCorrectName() {
        User user = new User("Frodo", "Baggins");
        User savedUser = registerUseCase.registerUser(user);
        assertThat(savedUser.getFirstName()).isEqualTo("Frodo");
        assertThat(savedUser.getLastName()).isEqualTo("Baggins");
    }

}

