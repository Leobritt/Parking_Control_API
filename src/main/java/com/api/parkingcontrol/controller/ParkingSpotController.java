package com.api.parkingcontrol.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
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

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.service.ParkingSpotService;

import jakarta.validation.Valid;

@RestController
/* acessado de qualquer fonte */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

	/* Ponto de injeção do service dentro do controller */
	final ParkingSpotService parkingSpotService;

	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}
	
	@GetMapping
	public ResponseEntity<List<ParkingSpotModel>> getAllParkingSPots(){
		return  ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
	}
	/* passar entre chaves pois será um path variable*/
	@GetMapping("/{id}")
	/*value do path variable será igual ao do mapping*/
	public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
		/*Optional métodos isPresent(), get(), orElse(), orElseGet(), orElseThrow()*/
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		/*validando existencia*/
		if (!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
		
	}
	
	 /* @RequestBody recebe os dados via json @Valid sem esta marcação as validações da DTO ñ são realizadas*/
	
	/* Post salvar novo registro */
	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
		/* Validação referente a regra de negocio */
		if (parkingSpotService.existsByLicensePlateCar(parkingSpotDTO.getLicensePlateCar())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
		}
		if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDTO.getParkingSpotNumber())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
		}
		if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDTO.getApartment(), parkingSpotDTO.getBlock())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
		}

		ParkingSpotModel parkingSpotModel = new ParkingSpotModel();

		/*
		 * Conversao DTO para model antes de salvar no BD passando oq vai ser convertido
		 * no que vai se tornar
		 */
		BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);

		/* setando o atributo RegistrationDate com localDateTime */
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteParkingSpot(@PathVariable (value = "id") UUID id){
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		
		/*validando existencia*/
		if (!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found!");
		}
		/*deletando registro*/
		parkingSpotService.delete(parkingSpotModelOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Parking spot deleted!");
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateParkingSPot(@PathVariable (value = "id") UUID id, @RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		
		/*validando existencia*/
		if (!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found!");
		}
		/*outra forma tirar o BeanUtils e delcarar o parkingSpotModel igualando com o parkingSpotOptional.get()*/
		/*setar todos os campos que podem ser atualizados e passar parkingSpotDTO.getParkingSpotNumber()... */
		ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
		/*Conversao DTO para model*/
		BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
		
		/*permanacer o mesmo id registration date*/
		parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
		parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(parkingSpotService.save(parkingSpotModel));

	}
}
