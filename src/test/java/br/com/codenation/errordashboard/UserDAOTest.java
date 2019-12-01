package br.com.codenation.errordashboard;

import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByName_returnsUserDetails() throws Exception{
        User savedUser = entityManager.persistFlushFind(new User(1L, "Leonardo",
                "lrodlima@gmail.com", "123456", LocalDateTime.now()));
        User user = userDAO.findByName("Leonardo");

        assertThat(user.getName()).isEqualTo(savedUser.getName());
        assertThat(user.getPassword()).isEqualTo(savedUser.getPassword());
    }
}
