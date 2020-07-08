package Sutton.Sutton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SuttonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuttonApplication.class, args);
	}
}
