package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotServices {

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotServices(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    // estou dizendo para o spring que em determinados momentos eu vou precisar que ele injete uma dependencia de parkingSpotRespository
    // @Autowired  // injeção de dependencias
    // ParkingSpotRepository parkingSpotRepository;

    @Transactional // se caso dê algo de errado nas transações, ele garante que volte como era antes
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);

    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
    return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
    return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public List<ParkingSpotModel> findAll() {
        return parkingSpotRepository.findAll(); // findAll serve para buscar todos os registros encontrados no banco de dados
    }

    public Optional<ParkingSpotModel> findById(Long id) {
    	System.out.println(id);
        return parkingSpotRepository.findById(id);
    }
    
    
    public List<ParkingSpotModel>findByResponsibleName(String responsible) {
    	System.out.println(responsible);
        return parkingSpotRepository.findByResponsibleName(responsible);
    }

    public List<ParkingSpotModel> findByParkingSpotNumber(String spot) {
        return parkingSpotRepository.findByParkingSpotNumber(spot); // findAll serve para buscar todos os registros encontrados no banco de dados
    }

    @Transactional // método para garatir a segurança dos dados
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }



}
