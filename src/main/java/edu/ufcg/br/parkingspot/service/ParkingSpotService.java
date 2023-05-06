package edu.ufcg.br.parkingspot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ufcg.br.parkingspot.model.ParkingSpot;
import edu.ufcg.br.parkingspot.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;

@Service
public class ParkingSpotService {

	@Autowired
	private ParkingSpotRepository parkingSpotRepository;

	@Transactional
	public ParkingSpot save(ParkingSpot parkingSpotModel) {
		
		return parkingSpotRepository.save(parkingSpotModel);
	}

	public boolean existsByLicensePlateCar(String licensePlateCar) {
		return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
	}

	public boolean existsByApartmentAndBlock(String apartment, String block) {
		return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
	}

	public List<ParkingSpot> findAll() {
		return parkingSpotRepository.findAll();
	}

	public Optional<ParkingSpot> findById(UUID id) {
		return parkingSpotRepository.findById(id);
	}

	public void delete(ParkingSpot parkingSpot) {
		parkingSpotRepository.delete(parkingSpot);		
	}
	
}
