package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.endDate >= ?1 ")
    List<Event> getUpcomingEvents(LocalDateTime date);
}
