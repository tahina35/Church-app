package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.SundayService;
import com.churchofphilippi.webserver.model.keys.SundayServicekey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SundayServiceRepository extends JpaRepository<SundayService, SundayServicekey> {


    @Query(
            value = "SELECT * FROM sunday_service ss WHERE ss.date <= ?1 AND ss.service_type = ?2 ORDER BY ss.date DESC LIMIT 1",
            nativeQuery = true
    )
    SundayService findLast(LocalDate date, int serviceType);
}
