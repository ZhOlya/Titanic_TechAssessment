package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController //Указывает, что класс будет обрабатывать HTTP запросы и возвращать в формате JSON
@EnableAutoConfiguration //Включает автоматическую конфигурацию Спринг бут
public class Application {

    private final PassengerService service;
    @Autowired//Указывает спринг на необходимость автоматически предоставить зависимость
    public Application(PassengerService service){
        this.service = service;
    }

    @GetMapping("/") //Обрабатывает GET запросы
    public List<Passenger> home(){ //возвращает список всех пассажиров
        return service.getAllPassenger();
    }

    @GetMapping("/passengers")
    public List<PassengerDto> getAllPassengers() { //возвращает список всех пассажиров в вижу DTO  с преображением pclss в числовое значение
        return service.getAllPassenger().stream()
                .map(passenger -> new PassengerDto(
                        passenger.getId(),
                        passenger.getPclass().toDbValue(),
                        passenger.getName(),
                        passenger.getSex(),
                        passenger.getAge(),
                        passenger.getSiblingsSpouses(),
                        passenger.getParentChildren(),
                        passenger.getFare()
                         // Используем метод toDbValue() для числового значения
                ))
                .collect(Collectors.toList());
    }

}
