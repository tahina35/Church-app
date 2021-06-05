package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.WednesdayService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface WednesdayServiceRepository extends JpaRepository<WednesdayService, Long> {

    void deleteByDate(LocalDate date);

    @Query(
            value = "SELECT * FROM wednesday_service ws WHERE ws.date <= ?1  ORDER BY ws.date DESC LIMIT 1",
            nativeQuery = true
    )
    Optional<WednesdayService> findLast(LocalDate sunday);
}
