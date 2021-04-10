package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.model.News;
import com.churchofphilippi.webserver.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/news")
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody News news) {
        return ResponseEntity.ok(newsService.save(news));
    }

    @GetMapping
    public ResponseEntity<?> getNews(@RequestParam("timeRange") int timeRange) {
        return ResponseEntity.ok(newsService.getNews(timeRange));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        newsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
