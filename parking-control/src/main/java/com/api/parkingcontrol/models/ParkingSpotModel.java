package com.api.parkingcontrol.models;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable { // isso é para conversões que vão ser realizadas, do tipo objetos Java para bytes com o objetivo de salvar no banco de dados
    private static final long serialVersionUID = 1L; // um controle que é feito pela JVM

    // simulação de controle de gerenciamento de vagas de um estacionameto no condominio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gerando de forma automatica
    private Long id; // tipos de identificadores apropriados e universais que ajuda na aquitetura distribuitiva
    @Column(nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;
    @Column(nullable = false, unique = true, length = 7)
    private String licensePlateCar;
    @Column(nullable = false, unique = true, length = 70)
    private String bradCar;
    @Column(nullable = false, unique = true, length = 70)
    private String modelCar;
    @Column(nullable = false, unique = true, length = 70)
    private String colorCar;
    @Column(nullable = false, unique = true, length = 70)
    private LocalDateTime registrationDate;
    @Column(nullable = false, unique = true, length = 130)
    private String responsibleName;
    @Column(nullable = false, unique = true, length = 30)
    private String apartment;
    @Column(nullable = false, unique = true, length = 30)
    private String block;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getBradCar() {
        return bradCar;
    }

    public void setBradCar(String bradCar) {
        this.bradCar = bradCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
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
