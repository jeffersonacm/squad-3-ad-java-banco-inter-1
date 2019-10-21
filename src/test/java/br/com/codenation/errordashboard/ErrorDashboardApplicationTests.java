package br.com.codenation.errordashboard;

import br.com.codenation.errordashboard.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ErrordashboardApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getUser_returnsUserDetails() throws Exception{

		//arrange

		//act
		ResponseEntity<User> response = restTemplate.getForEntity("/users/disouzaleo", User.class);

		//assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getFirstName()).isEqualTo("Leonardo");
		assertThat(response.getBody().getLastName()).isEqualTo("Rodrigues");
	}

}
