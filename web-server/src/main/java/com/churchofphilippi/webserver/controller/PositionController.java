package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.model.Position;
import com.churchofphilippi.webserver.model.pagination.PositionPage;
import com.churchofphilippi.webserver.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/position")
public class PositionController {

    private final PositionService positionService;

    @GetMapping("/page/{pageNo}")
    public ResponseEntity<?> getPositionPaginated(@PathVariable("pageNo")int pageNo) {
        int pagseSize = 10;
        Page<Position> paginated = positionService.findPaginated(pageNo, pagseSize);
        PositionPage page = new PositionPage(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDept(@RequestParam(name = "v") String value, @RequestParam(name = "page") int pageNo) {
        int pagseSize = 10;
        Page<Position> paginated = positionService.findAllWithFilters(value, pageNo, pagseSize);
        PositionPage page = new PositionPage(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Position position) {
        return ResponseEntity.ok(positionService.save(position));
    }

    @GetMapping
    public ResponseEntity<?> getPositions() {
        return ResponseEntity.ok(positionService.getAll());
    }
}
