package com.api.parkingcontrol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*DTO usado para o transporte de dados entre diferentes componentes de um sistema*/
public class ParkingSpotDTO {

	/* dependencia starter validation */
	/*Campos que serão enviados pelo cliente*/
	@NotBlank
	private String parkingSpotNumber;

	@NotBlank
	@Size(max = 7)
	private String licensePlateCar;

	@NotBlank
	private String brandCar;

	@NotBlank
	private String modelCar;

	@NotBlank
	private String colorlCar;

	@NotBlank
	private String responsibleName;

	@NotBlank
	private String apartment;

	@NotBlank
	private String block;

	public String getParkingSpotNumber() {
		return parkingSpotNumber;
	}

	public void setParkingSpotNumber(String parkingSpotNumber) {
		this.parkingSpotNumber = parkingSpotNumber;
	}

	public String getLicensePlateCar() {
		return licensePlateCar;
	}

	public void setLicensePlateCar(String licensePlateCar) {
		this.licensePlateCar = licensePlateCar;
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

	public String getColorlCar() {
		return colorlCar;
	}

	public void setColorlCar(String colorlCar) {
		this.colorlCar = colorlCar;
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

}
