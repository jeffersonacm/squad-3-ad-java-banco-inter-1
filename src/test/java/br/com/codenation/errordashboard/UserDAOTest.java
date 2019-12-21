package br.com.codenation.errordashboard;

import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByEmail_thenReturnUser() {
        //given
        User alex = new User(1L, "Alex", "alex@gmail.com",
                "12345667", "", LocalDateTime.now(), new ArrayList<>());
        userDAO.save(alex);


        //when
        User userFound = userDAO.findByEmail(alex.getEmail());

        //then
        assertThat(userFound.getEmail())
                .isEqualTo(alex.getEmail());
    }
}
