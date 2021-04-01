package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.EarlyMorningServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EarlyMorningServicesRepository extends JpaRepository<EarlyMorningServices, Long> {

    void deleteByDate(LocalDate date);
}
