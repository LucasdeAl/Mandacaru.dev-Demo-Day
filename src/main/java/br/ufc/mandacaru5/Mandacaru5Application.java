package br.ufc.mandacaru5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Mandacaru5Application {

	public static void main(String[] args) {
		SpringApplication.run(Mandacaru5Application.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha3"));
	}
}
