package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.creationDate >= ?1 ORDER BY n.creationDate DESC")
    List<Notification> getLast30daysNotification(LocalDateTime date);
}
