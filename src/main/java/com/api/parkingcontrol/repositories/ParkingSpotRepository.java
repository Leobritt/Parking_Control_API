package com.api.parkingcontrol.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parkingcontrol.model.ParkingSpotModel;
/*passa o model e tipo do identificador*/
/*JpaRepository faz o CRUD*/

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID>{

    boolean existsByLicensePlateCar(String licensePlateCar);

    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    /*Por causa do And passa dois parametros*/
    boolean existsByApartmentAndBlock(String apartment, String block);
    
   
}
