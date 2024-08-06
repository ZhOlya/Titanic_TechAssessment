package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service //Класс является сервисом (логикой) приложения
 public class PassengerService {
    private final PassengerRepository repository;
    @Autowired //необходимость автоматически предоставить зависимость
    public PassengerService(PassengerRepository repository){
        this.repository = repository;
    }
    private static final Set<String> VALID_SORT_FIELDS = new HashSet<>(Arrays.asList("name", "age", "fare", "id"));

    public List<Passenger> getAllPassenger() {
        return repository.findAll();
    }

    public Page <Passenger> getPassengers (int page, int size, String sortField, String sortDir, String searchName,
                                           boolean filterSurvived, boolean filterAdult, boolean filterMale, boolean filterNoRelatives){

        //Проверка sortField на null и пустоту, если так, устанавливает на name и проверим его допустимость
        if (sortField == null || sortField.isEmpty() || !VALID_SORT_FIELDS.contains(sortField)){
            sortField = "name";
        }
        // Создаем объект сортировки, который определяет по какму полю сортировать и в каком направлении
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        //Объект, который включает в себя номер страницы, ее размер и параметры сортировки
        Pageable pageable = PageRequest.of(page, size, sort);

        if(searchName == null || searchName.isEmpty()){
            if (filterSurvived || filterAdult || filterMale || filterNoRelatives){
                return repository.findFiltered(filterSurvived, filterAdult, filterMale, filterNoRelatives, pageable);
            } else {
                return repository.findAll(pageable);
            }
        } else {
            if (filterSurvived || filterAdult || filterMale || filterNoRelatives){
                return repository.findFilterByName(searchName, filterSurvived, filterAdult, filterMale, filterNoRelatives, pageable);
            } else {
                return repository.findByNameContainingIgnoreCase(searchName, pageable);
            }
        }

    }

}
