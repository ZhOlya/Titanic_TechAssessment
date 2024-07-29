package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PassengerController {
    private final PassengerService service;
    @Autowired//Указывает спринг на необходимость автоматически предоставить зависимость
    public PassengerController (PassengerService service){
        this.service = service;
    }

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "50") int size,
                       Model model) {
        Page<Passenger> passengerPage = service.getPassengers(PageRequest.of(page, size));
        List<PassengerDto> passengers = passengerPage.stream()
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
                )).collect(Collectors.toList());
        model.addAttribute("passengers", passengers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", passengerPage.getTotalPages());
        return "passengers";
    }

//    @GetMapping("/") //Обрабатывает GET запросы
//    public String home(Model model){ //возвращает список всех пассажиров в как таблицу
//        List<PassengerDto> passengers = service.getAllPassenger().stream().
//                map(passenger -> new PassengerDto(
//                        passenger.getId(),
//                        passenger.getSurvived(),
//                        passenger.getPclass().toDbValue(),
//                        passenger.getName(),
//                        passenger.getSex(),
//                        passenger.getAge(),
//                        passenger.getSiblingsSpouses(),
//                        passenger.getParentChildren(),
//                        passenger.getFare()
//                )).collect(Collectors.toList());
//        model.addAttribute("passengers", passengers);
//        return "passengers";
//    }
}
