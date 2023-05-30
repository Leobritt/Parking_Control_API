package com.api.parkingcontrol.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

import jakarta.transaction.Transactional;

/*camada referente a regra de negocios e comunicação entre controller e o repository*/
@Service
/* depois criar uma interface e implemntar nesta classe */
public class ParkingSpotService {

	/* Ponto de injeção do repository dentro do controller */

	final ParkingSpotRepository parkingSpotRepository;

	public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
	}

	/* Usando o JPA */
	/* Método CREATE */
	@Transactional /* Garante o rollback */
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		return parkingSpotRepository.save(parkingSpotModel);
	}

	/* Método READ */
	public List<ParkingSpotModel> findAll() {
		return parkingSpotRepository.findAll();
	}

	/* Método READ by id */
	public Optional<ParkingSpotModel> findById(UUID id) {
		return parkingSpotRepository.findById(id);
	}
	@Transactional
	/* Método DELETE */
	public void delete(ParkingSpotModel parkingSpotModel) {	
		parkingSpotRepository.delete(parkingSpotModel);
	}

	/*
	 * é necessario delcarar este método dentro da Interface Repository, pois o JPA
	 * não possui pronto
	 */
	public boolean existsByLicensePlateCar(String licensePlateCar) {
		return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
	}

	public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
		return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
	}

	public boolean existsByApartmentAndBlock(String apartment, String block) {
		return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
	}

}
