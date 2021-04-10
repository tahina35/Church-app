package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.Event;
import com.churchofphilippi.webserver.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService implements BaseService<Event> {

    private final EventRepository eventRepository;

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event entity) {
        return eventRepository.save(entity);
    }

    @Override
    public void delete(Event entity) {
        eventRepository.delete(entity);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getUpcomingEvents() {
        LocalDateTime monthStart = LocalDateTime.now().withDayOfMonth(1).plusDays(1);
        return eventRepository.getUpcomingEvents(monthStart);
    }

    public Event getById(Long id) {
        return eventRepository.getOne(id);
    }
}
