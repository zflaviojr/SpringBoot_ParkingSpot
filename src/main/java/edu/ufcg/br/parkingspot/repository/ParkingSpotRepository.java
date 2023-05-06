package edu.ufcg.br.parkingspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ufcg.br.parkingspot.model.ParkingSpot;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, UUID>{

	public boolean existsByLicensePlateCar(String licensePlateCar);
	
	public boolean existsByApartmentAndBlock(String apartment, String block);
	
}