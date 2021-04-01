package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.WednesdayService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WednesdayServiceRepository extends JpaRepository<WednesdayService, Long> {

    void deleteByDate(LocalDate date);
}
