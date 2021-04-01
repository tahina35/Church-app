package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.config.PageConfig;
import com.churchofphilippi.webserver.model.Position;
import com.churchofphilippi.webserver.model.pagination.CustomPage;
import com.churchofphilippi.webserver.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/position")
public class PositionController {

    private final PositionService positionService;
    private final PageConfig pageConfig;

    @GetMapping("/page/{pageNo}")
    public ResponseEntity<?> getPositionPaginated(@PathVariable("pageNo")int pageNo) {
        Page<Position> paginated = positionService.findPaginated(pageNo, pageConfig.getSize());
        CustomPage<Position> page = new CustomPage<Position>(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDept(@RequestParam(name = "v") String value, @RequestParam(name = "page") int pageNo) {
        Page<Position> paginated = positionService.findAllWithFilters(value, pageNo, pageConfig.getSize());
        CustomPage<Position> page = new CustomPage<Position>(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        positionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
