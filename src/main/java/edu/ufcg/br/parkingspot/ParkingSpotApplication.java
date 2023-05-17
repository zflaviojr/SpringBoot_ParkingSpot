package edu.ufcg.br.parkingspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ParkingSpotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingSpotApplication.class, args);
		
		String senha = new BCryptPasswordEncoder().encode("senha123");
		System.out.println("Senha123: " + senha);
	}
	
	@GetMapping("/")
	public String index(){
		return "Olá mundo com Spring Boot!";
	}

}
