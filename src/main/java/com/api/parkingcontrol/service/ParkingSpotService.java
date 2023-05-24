package com.api.parkingcontrol.service;

import org.springframework.stereotype.Service;

import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

import jakarta.transaction.Transactional;

/*camada referente a regra de negocios e comunicação entre controller e o repository*/
@Service
/*depois criar uma interface e implemntar nesta classe*/
public class ParkingSpotService {
	
	
	/*Ponto de injeção do repository dentro do controller*/
	
	final ParkingSpotRepository  parkingSpotRepository;

	public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
	}
	 
	/*Usando o JPA*/
	@Transactional /*Garante o rollback*/
	 public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {	 
		return parkingSpotRepository.save(parkingSpotModel);
	 }
	 
}
