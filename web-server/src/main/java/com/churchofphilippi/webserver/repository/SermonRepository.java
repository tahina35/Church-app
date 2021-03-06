package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SermonRepository extends JpaRepository<Sermon, Long> {
}
