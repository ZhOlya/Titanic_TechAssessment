package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page <Passenger> getPassengers (int page, int size, String sortField, String sortDir, String searchName){
        // Создаем объект сортировки, который определяет по какму полю сортировать и в каком направлении
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        //Объект, который включает в себя номер страницы, ее размер и параметры сортировки
        Pageable pageable = PageRequest.of(page, size, sort);
        //Если искомое имя отсутсвует, то взвращаем все записи
        if (searchName == null || searchName.isEmpty()){
            return repository.findAll(pageable);
        } else {
            return repository.findByNameContainingIgnoreCase(searchName, pageable);
        }
    }

}
