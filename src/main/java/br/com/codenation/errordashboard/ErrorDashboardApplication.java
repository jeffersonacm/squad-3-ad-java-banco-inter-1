package br.com.codenation.errordashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class ErrorDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorDashboardApplication.class, args);
	}

}
