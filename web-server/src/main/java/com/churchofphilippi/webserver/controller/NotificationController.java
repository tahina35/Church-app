package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.model.News;
import com.churchofphilippi.webserver.model.Notification;
import com.churchofphilippi.webserver.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.save(notification));
    }

    @GetMapping
    public ResponseEntity<?> getNotifications() {
        return ResponseEntity.ok(notificationService.getRecentNotifications());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Notification notification) {
        notificationService.delete(notification);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long id) {
        notificationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
