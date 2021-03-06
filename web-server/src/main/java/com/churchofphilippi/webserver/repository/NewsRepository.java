package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.News;
import com.churchofphilippi.webserver.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
