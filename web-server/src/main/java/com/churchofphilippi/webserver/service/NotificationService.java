package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.config.MobileConfig;
import com.churchofphilippi.webserver.model.Notification;
import com.churchofphilippi.webserver.repository.NotificationRepository;
import com.google.firebase.messaging.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService implements BaseService<Notification> {

    private final NotificationRepository notificationRepository;
    private final FCMTokenService tokenService;
    private final MobileConfig mobileConfig;

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification save(Notification entity) {
        Notification notif = notificationRepository.save(entity);
        String topic = tokenService.setTopic(notif.getDepartment());
        String content = notif.getContent().replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        content = (content.length() > 150) ? content.substring(0,150) + "..." : content;
        String title = "New message";
        String body = content;
        tokenService.sendNotification(title, body, topic);
        return notif;
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
