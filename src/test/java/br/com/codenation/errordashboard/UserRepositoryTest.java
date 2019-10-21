package br.com.codenation.errordashboard;

import br.com.codenation.errordashboard.domain.User;
import br.com.codenation.errordashboard.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByUsername_returnsUserDetails() throws Exception{
        User savedUser = entityManager.persistFlushFind(new User("Leonardo", "Rodrigues", "disouzaleo"));
        User user = repository.findByUserName("disouzaleo");

        assertThat(user.getFirstName()).isEqualTo(savedUser.getFirstName());
        assertThat(user.getLastName()).isEqualTo(savedUser.getLastName());
    }
}
