package com.api.parkingcontrol.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PARKING_SPOT")
/*
 * Ao implementar a interface Serializable, a classe ParkingSpotModel permite
 * que seus objetos sejam serializados e desserializados.É útil em
 * cenários onde você precisa salvar objetos dessa classe em arquivos ou
 * transmiti-los pela rede.
 */
public class ParkingSpotModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*depois criar outra classe carro*/
	
	@Id
	/*gerado de forma auto*/
	@GeneratedValue(strategy = GenerationType.AUTO)
	/*UUID identificador distribuido usado em micro services*/
	private UUID id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String parkingSpotNumber;
	
	@Column(nullable = false, unique = true, length = 10)
	private String licensePlateCar;
	
	@Column(nullable = false, length = 70)
	private String brandCar;
	
	@Column(nullable = false, length = 70)
	private String modelCar;
	
	@Column(nullable = false, length = 70)
	private String colorlCar;
	
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	
	@Column(nullable = false, length = 120)
	private String responsibleName;
	
	@Column(nullable = false, length = 30)
	private String apartment;
	
	@Column(nullable = false, length = 30)
	private String block;
	
	
}
