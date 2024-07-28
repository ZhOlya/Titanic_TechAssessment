package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
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



    @GetMapping("/passengers")
    public List<PassengerDto> getAllPassengers() { //возвращает список всех пассажиров в вижу DTO  с преображением pclss в числовое значение и вывод в JSON
        return service.getAllPassenger().stream()
                .map(passenger -> new PassengerDto(
                        passenger.getId(),
                        passenger.getSurvived(),
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
