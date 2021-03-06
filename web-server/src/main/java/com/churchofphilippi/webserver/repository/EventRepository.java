package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
