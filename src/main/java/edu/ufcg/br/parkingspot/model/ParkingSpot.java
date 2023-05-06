package edu.ufcg.br.parkingspot.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "parking_spot")
public class ParkingSpot implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "parking_spot_ref", nullable = false, unique = true, length = 10)
	private String parkingSpotRef;
	
	@Column(name = "brand_car", nullable = false, length = 20)
	private String brandCar;
	
	@Column(name = "model_car", nullable = false, length = 50)
	private String modelCar;
	
	@Column(name = "color_car", nullable = false, length = 20)
	private String colorCar;
	
	@Column(name = "license_plate_car", nullable = false, length = 10)
	private String licensePlateCar;
	
	@Column(name = "responsible_name", nullable = false, length = 130)
	private String responsibleName;
	
	//@Column(name = "apartment") //the same name, so, isn't necessary to mapping
	private String apartment;
	
	//@Column(name = "block") //the same name, so, isn't necessary to mapping
	private String block;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime registrationDate;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getParkingSpotRef() {
		return parkingSpotRef;
	}

	public void setParkingSpotRef(String parkingSpotRef) {
		this.parkingSpotRef = parkingSpotRef;
	}

	public String getBrandCar() {
		return brandCar;
	}

	public void setBrandCar(String brandCar) {
		this.brandCar = brandCar;
	}

	public String getModelCar() {
		return modelCar;
	}

	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}

	public String getColorCar() {
		return colorCar;
	}

	public void setColorCar(String colorCar) {
		this.colorCar = colorCar;
	}

	public String getLicensePlateCar() {
		return licensePlateCar;
	}

	public void setLicensePlateCar(String licensePlateCar) {
		this.licensePlateCar = licensePlateCar;
	}

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

}
