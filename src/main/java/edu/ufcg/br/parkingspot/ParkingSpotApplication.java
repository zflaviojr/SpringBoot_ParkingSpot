package edu.ufcg.br.parkingspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ufcg.br.parkingspot.controller.ParkingSpotController;

@SpringBootApplication
@RestController
public class ParkingSpotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingSpotApplication.class, args);
		
		String senha = new BCryptPasswordEncoder().encode("senha123");
		System.out.println(senha);
	}
	
	@GetMapping("/")
	public String index(){
		return "Olá mundo com Spring Boot!";
	}

}
