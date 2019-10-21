package br.com.codenation.errordashboard;

import br.com.codenation.errordashboard.domain.User;
import br.com.codenation.errordashboard.repository.UserRepository;
import br.com.codenation.errordashboard.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository);
    }

    @Test
    public void getUserDetails_returnUserInfo() {
        given(userRepository.findByUserName("disouzaleo")).willReturn(new User("Leonardo", "Rodrigues", "disouzaleo"));

        User user = userService.getUserDetails("disouzaleo");

        assertThat(user.getFirstName()).isEqualTo("Leonardo");
        assertThat(user.getLastName()).isEqualTo("Rodrigues");
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserDetails_whenUserNotFound() throws Exception {
        given(userRepository.findByUserName("disouzaleo")).willReturn(null);

        userService.getUserDetails("disouzaleo");
    }

    @Test
    public void getAllUsers() throws Exception {

    }
}
