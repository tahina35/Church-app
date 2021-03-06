package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.Event;
import com.churchofphilippi.webserver.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EventService implements BaseService<Event> {

    private EventRepository eventRepository;

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
}