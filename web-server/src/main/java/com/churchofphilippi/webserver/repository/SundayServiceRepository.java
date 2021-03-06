package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.SundayService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SundayServiceRepository extends JpaRepository<SundayService, Long> {
}
