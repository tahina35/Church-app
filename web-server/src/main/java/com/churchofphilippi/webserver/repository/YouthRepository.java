package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.SundayService;
import com.churchofphilippi.webserver.model.YouthService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface YouthRepository extends JpaRepository<YouthService, Long> {

    void deleteByDate(LocalDate date);

    @Query(
            value = "SELECT * FROM youth_service ys WHERE ys.date <= ?1  ORDER BY ys.date DESC LIMIT 1",
            nativeQuery = true
    )
    Optional<YouthService> findLast(LocalDate date);
}
