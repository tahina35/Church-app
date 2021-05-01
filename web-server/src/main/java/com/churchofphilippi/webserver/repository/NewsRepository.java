package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.News;
import com.churchofphilippi.webserver.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE n.endDate >= ?1 AND n.endDate <= ?2 ORDER BY n.newsId DESC")
    List<News> getByDate(LocalDate startDate, LocalDate endDate);

    @Query("SELECT n FROM News n WHERE n.endDate >= ?1 ORDER BY n.newsId DESC")
    List<News> getByDate(LocalDate startDate);

    @Query("SELECT n FROM News n WHERE n.startDate <= ?1 AND n.endDate >= ?1 ORDER BY n.newsId DESC")
    List<News> getNews(LocalDate startDate);
}