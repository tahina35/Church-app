package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.config.MobileConfig;
import com.churchofphilippi.webserver.model.Event;
import com.churchofphilippi.webserver.repository.EventRepository;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService implements BaseService<Event> {

    private final EventRepository eventRepository;
    private final FCMTokenService tokenService;
    private final MobileConfig mobileConfig;

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event entity) {
        Event event = eventRepository.save(entity);
        String topic = tokenService.setTopic(event.getDepartment());
        String title = "New event shared";
        String body = event.getTitle();
        tokenService.sendNotification(title, body, topic);
        return event;
    }

    @Override
    public void delete(Event entity) {
        eventRepository.delete(entity);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getUpcomingEvents() {
        LocalDateTime monthStart = LocalDateTime.now().minusMonths(6);
        return eventRepository.getUpcomingEvents(monthStart);
    }

    public Event getById(Long id) {
        return eventRepository.getOne(id);
    }
}
