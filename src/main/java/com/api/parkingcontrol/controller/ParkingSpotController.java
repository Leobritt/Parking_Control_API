package com.api.parkingcontrol.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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

	/*Ponto de injeção do service dentro do controller*/
	final ParkingSpotService parkingSpotService;

	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}
	
	/*Post salvar novo registro*/	
	@PostMapping
	/*ResponseEntity retorna status http e body o <Object> possibilita diferentes tipos de retornos*/
	/*@RequestBody recebe os dados via json 
	 * @Valid sem esta marcação as validações da DTO ñ são realizadas*/
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
		/*Instanciando*/
		var parkingSpotModel = new ParkingSpotModel();
		
		/*Conversao DTO para model antes de salvar no BD passando oq vai ser convertido no que vai se tornar*/
		BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
		
		/*setando o atributo RegistrationDate com localDateTime*/
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
	}
}
