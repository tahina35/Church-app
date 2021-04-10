package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.Notification;
import com.churchofphilippi.webserver.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService implements BaseService<Notification> {

    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification save(Notification entity) {
        return notificationRepository.save(entity);
    }

    @Override
    public void delete(Notification entity) {
        notificationRepository.delete(entity);
    }

    public List<Notification> getRecentNotifications() {
        LocalDateTime last30days = LocalDateTime.now().minusDays(30);
        return notificationRepository.getLast30daysNotification(last30days);
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }
}
