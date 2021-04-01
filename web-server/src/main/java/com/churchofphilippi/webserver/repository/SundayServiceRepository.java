package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.SundayService;
import com.churchofphilippi.webserver.model.keys.SundayServicekey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SundayServiceRepository extends JpaRepository<SundayService, SundayServicekey> {

}
