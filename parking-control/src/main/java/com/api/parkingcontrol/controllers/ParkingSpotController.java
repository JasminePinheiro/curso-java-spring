package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import com.api.parkingcontrol.services.ParkingSpotServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
	@Autowired
	ParkingSpotRepository pRepo;

	@GetMapping("/spots")
	public ResponseEntity<List<ParkingSpotModel>> findByParkingSpotNumber(@RequestParam String spot) {
		return new ResponseEntity<List<ParkingSpotModel>>(pRepo.findByParkingSpotNumber(spot), HttpStatus.OK);
	}

	
    // ponto de injeção via serve para o construtor
    final ParkingSpotServices parkingSpotServices;

    public ParkingSpotController(ParkingSpotServices parkingSpotServices) {
        this.parkingSpotServices = parkingSpotServices;
    }

    // serve para salvar os regitros das vagas
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        if (parkingSpotServices.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Confict: License Plate Car is already in use!");
        }
        if (parkingSpotServices.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Confict:  Parking Spot is already in use!");
        }
        if (parkingSpotServices.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Confict: Parking Spot already registered for this apartment/block!");
        }


        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); // fazendo a converção de parkingSpotDto para parkingSpotModel
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC"))); // setando a data
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotServices.save(parkingSpotModel));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotServices.findAll()); // busca todos os registro do banco de dados
    }

    // ********* Verificar o erro *********
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") Long id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotServices.findById(id);
        return parkingSpotModelOptional.<ResponseEntity<Object>>map(parkingSpotModel -> ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found"));
    }

    @GetMapping("/responsible/{responsibleName}")
	public ResponseEntity<List<ParkingSpotModel>> getByResponsibleName(@PathVariable(value = "responsibleName") String id) {
    	return new ResponseEntity<List<ParkingSpotModel>>(parkingSpotServices.findByResponsibleName(id), HttpStatus.OK);
    		 }
  

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") Long id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotServices.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
        }
        parkingSpotServices.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully");
    }
    
    // atualiza
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") Long id, @RequestBody @Valid ParkingSpotDto parkingSpotDto){
    	Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotServices.findById(id);
    	if (!parkingSpotModelOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paking Spot not found");
    		
    	}
    	
    	// quero que ele copie todos os dados deixando, mantendo apenas a data de registro e o id
    	var parkingSpotModel = new ParkingSpotModel(); // criando uma nova instacia de parkingModel
    	BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
    	parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
    	parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
    	
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotServices.save(parkingSpotModel));
    	
    }
    
    



	// <Object> teremos diferentes tipos de retorno, dependendo as nossa
	// verificações
	// "UTC" = Tempo Universal Coordenado

}
