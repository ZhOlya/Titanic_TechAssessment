package org.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//ИФ для работы с БД. Наследует методы для стандартных операция CRUD (Создание, чтение, обновление, удаление)
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Page <Passenger> findByNameContainingIgnoreCase (String name, Pageable pageable);

    @Query("SELECT p FROM Passenger p WHERE "
    + "(p.survived = :survived OR :survived = false) AND "
    + "(p.age > 16 OR :filterAdult = false) AND "
    + "(p.sex = 'male' OR :filterMale = false) AND "
    + "(p.siblingsSpouses = 0 AND p.parentChildren = 0 OR :filterNoRelatives = false)")
    Page<Passenger>findFiltered (@Param("survived") boolean survived,
                                 @Param("filterAdult") boolean filterAdult,
                                 @Param("filterMale") boolean filterMale,
                                 @Param("filterNoRelatives") boolean filterNoRelatives,
                                 Pageable pageable);

    @Query("SELECT p FROM Passenger p WHERE p.name LIKE %:name% AND "
    + "(p.survived = :survived OR :survived = false) AND "
    + "(p.age > 16 OR :filterAdult = false) AND "
    + "(p.sex = 'male' OR :filterMale = false) AND "
    + "(p.siblingsSpouses = 0 AND p.parentChildren = 0 OR :filterNoRelatives = false)")
    Page<Passenger> findFilterByName(@Param("name") String name,
                                     @Param("survived") boolean survived,
                                     @Param("filterAdult") boolean filterAdult,
                                     @Param("filterMale") boolean filterMale,
                                     @Param("filterNoRelatives") boolean filterNoRelatives,
                                     Pageable pageable);

}
