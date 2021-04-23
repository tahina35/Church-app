package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.EarlyMorningServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EarlyMorningServicesRepository extends JpaRepository<EarlyMorningServices, Long> {

    void deleteByDate(LocalDate date);

    @Query(
            value = "SELECT * FROM early_morning_services ems WHERE ems.date < ?1 ORDER BY ems.date DESC LIMIT 5",
            nativeQuery = true
    )
    List<EarlyMorningServices> findLast5(LocalDate date);
}
