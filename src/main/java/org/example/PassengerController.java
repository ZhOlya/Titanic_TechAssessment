package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/") //гет запрос на корневой url
    public String home(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "50") int size,
                       @RequestParam(defaultValue = "name") String sortField,// выбор поля для сортировки
                       @RequestParam(defaultValue = "asc") String sortDir, // выбор сортировки по возр или убыв
                       @RequestParam(defaultValue = "") String searchName,
                       @RequestParam(defaultValue = "false") boolean filterSurvived,
                       @RequestParam(defaultValue = "false") boolean filterAdult,
                       @RequestParam(defaultValue = "false") boolean filterMale,
                       @RequestParam(defaultValue = "false") boolean filterNoRelatives,
                       Model model) {
//Получение страницы пассажиров с учетом всех фильтров и сортировки
        Page<Passenger> passengerPage = service.getPassengers(page, size, sortField, sortDir, searchName,
                filterSurvived, filterAdult, filterMale, filterNoRelatives);

        //Преобразование страницы  пассажирова в список DTO
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
        model.addAttribute("pageSize", size); //добавляем размер страницы в модель (выбор количества записей на странице)
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("searchName", searchName);
        model.addAttribute("filterSurvived", filterSurvived);
        model.addAttribute("filterAdult", filterAdult);
        model.addAttribute("filterMale", filterMale);
        model.addAttribute("filterNoRelatives", filterNoRelatives);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
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
