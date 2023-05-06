package edu.ufcg.br.parkingspot.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ufcg.br.parkingspot.dto.ParkingSpotDTO;
import edu.ufcg.br.parkingspot.model.ParkingSpot;
import edu.ufcg.br.parkingspot.service.ParkingSpotService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 360)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
	
	@Autowired
	private ParkingSpotService parkingSpotService;
	
	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDto) {
		
		if( parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar()) ) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Car License Plate already in use!");
		}
		
		if( parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock()) ) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Apartament from this Block already in use!");
		}
		
		var parkingSpotModel = new ParkingSpot();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println("###########Erro: " + e.getCause());
			throw e;			
		}
			
	}
	
	@GetMapping("/findAll")
	private ResponseEntity<List<ParkingSpot>> findAllParkingSpots() {
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Object> findParkingSpotById(@PathVariable(value = "id") UUID id) {
		Optional<ParkingSpot> parkingSpotOptional = parkingSpotService.findById(id);
		if( !parkingSpotOptional.isPresent() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotOptional.get()); 

	}

	@DeleteMapping("/{id}")
	private ResponseEntity<Object> deleteParkingSpotById(@PathVariable(value = "id") UUID id) {
		Optional<ParkingSpot> parkingSpotOptional = parkingSpotService.findById(id);
		if( !parkingSpotOptional.isPresent() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");
		}
		parkingSpotService.delete(parkingSpotOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Parking Spot sucessfully deleted!");

	}

	//Update a Parking Spot
	@PutMapping("/{id}")
	private ResponseEntity<Object> updateParkingSpotById(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
		Optional<ParkingSpot> parkingSpotOptional = parkingSpotService.findById(id);
		if( !parkingSpotOptional.isPresent() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");
		}
		
		/* updating in conventional manner
		var parkingSpot = parkingSpotOptional.get();
		parkingSpot.setLicensePlateCar(parkingSpotDTO.getLicensePlateCar());
		parkingSpot.setModelCar(parkingSpotDTO.getModelCar());
		parkingSpot.setBrandCar(parkingSpotDTO.getBrandCar());
		parkingSpot.setColorCar(parkingSpotDTO.getColorCar());
		parkingSpot.setApartment(parkingSpotDTO.getApartment());
		parkingSpot.setBlock(parkingSpotDTO.getBlock());
		parkingSpot.setResponsibleName(parkingSpotDTO.getResponsibleName());
		*/
		
		//better choice
		var parkingSpotNew = new ParkingSpot();
		var parkingSpot = parkingSpotOptional.get();
		BeanUtils.copyProperties(parkingSpotDTO, parkingSpotNew);
		parkingSpotNew.setId(parkingSpot.getId());
		parkingSpotNew.setRegistrationDate(parkingSpot.getRegistrationDate());
		
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotNew));

	}

	
}
