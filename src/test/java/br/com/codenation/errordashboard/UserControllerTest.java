package br.com.codenation.errordashboard;

import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.endpoints.UserController;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUser_ShouldReturnUser() throws Exception {
        //given(userService.getUserDetails(anyString())).willReturn(new UserService.UserRepositoryUserDetails(new User()));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/disouzaleo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("Leonardo"))
                .andExpect(jsonPath("lastName").value("Rodrigues"));

    }

    @Test
    public void getUser_notFound() throws Exception {
        given(userService.getUserDetails(anyString())).willThrow(new UserNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/disouzaleo"))
                .andExpect(status().isNotFound());
    }
}
