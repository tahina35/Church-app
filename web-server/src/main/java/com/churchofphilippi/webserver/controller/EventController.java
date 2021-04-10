package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.model.Event;
import com.churchofphilippi.webserver.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/event")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.save(event));
    }

    @GetMapping
    public ResponseEntity<?> getUpcomingEvents() {
        return ResponseEntity.ok(eventService.getUpcomingEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        eventService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
