package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Member;
import com.churchofphilippi.webserver.model.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SermonRepository extends JpaRepository<Sermon, Long> {
}
