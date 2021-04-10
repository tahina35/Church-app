package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.YouthService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface YouthRepository extends JpaRepository<YouthService, Long> {

    void deleteByDate(LocalDate date);
}
