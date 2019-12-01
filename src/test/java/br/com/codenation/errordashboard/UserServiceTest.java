package br.com.codenation.errordashboard;

import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userDAO);
    }

    @Test
    public void getUserDetails_returnUserInfo() {
        given(userDAO.findByName("Leonardo"))
                .willReturn(new User(1L, "Leonardo",
                        "lrodlima@gmail.com", "123456", LocalDateTime.now()));

        User user = userService.getUserDetails("disouzaleo");

        assertThat(user.getName()).isEqualTo("Leonardo");
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserDetails_whenUserNotFound() throws Exception {
        given(userDAO.findByName("disouzaleo")).willReturn(null);

        userService.getUserDetails("disouzaleo");
    }

    @Test
    public void getAllUsers_returnsUserList() throws Exception {
    }
}
