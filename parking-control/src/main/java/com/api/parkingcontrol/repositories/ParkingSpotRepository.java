package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, Long>  { // JPA ajuda nas transações com o banco de dados



    List<ParkingSpotModel> findByParkingSpotNumber(String parkingSpotNumber);
    @Query(value = "select * from tb_parking_spot where responsible_name=?",nativeQuery = true)
    List<ParkingSpotModel>  findByResponsibleName(String responsible);
    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartmentAndBlock(String apartment, String block);



    }
