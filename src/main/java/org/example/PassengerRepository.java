package org.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//ИФ для работы с БД. Наследует методы для стандартных операция CRUD (Создание, чтение, обновление, удаление)
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Page <Passenger> findAll (Pageable pageable);

}
