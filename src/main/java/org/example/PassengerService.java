package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //Класс является сервисом (логикой) приложения
 public class PassengerService {
    private final PassengerRepository repository;
    @Autowired //необходимость автоматически предоставить зависимость
    public PassengerService(PassengerRepository repository){
        this.repository = repository;
    }

    public List<Passenger> getAllPassenger() {
        return repository.findAll();
    }

}
