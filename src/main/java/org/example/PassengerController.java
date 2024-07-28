package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PassengerController {
    private final PassengerService service;
    @Autowired//Указывает спринг на необходимость автоматически предоставить зависимость
    public PassengerController (PassengerService service){
        this.service = service;
    }

    @GetMapping("/") //Обрабатывает GET запросы
    public String home(Model model){ //возвращает список всех пассажиров в как таблицу
        List<PassengerDto> passengers = service.getAllPassenger().stream().
                map(passenger -> new PassengerDto(
                        passenger.getId(),
                        passenger.getSurvived(),
                        passenger.getPclass().toDbValue(),
                        passenger.getName(),
                        passenger.getSex(),
                        passenger.getAge(),
                        passenger.getSiblingsSpouses(),
                        passenger.getParentChildren(),
                        passenger.getFare()
                )).collect(Collectors.toList());
        model.addAttribute("passengers", passengers);
        return "passengers";
    }
}
