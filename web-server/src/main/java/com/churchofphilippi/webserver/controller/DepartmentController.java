package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.pagination.DeptPage;
import com.churchofphilippi.webserver.service.DeptService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/department")
public class DepartmentController {

    private final DeptService deptService;

    @GetMapping("/page/{pageNo}")
    public ResponseEntity<?> getDeptssPaginated(@PathVariable("pageNo")int pageNo) {
        int pagseSize = 10;
        Page<Dept> paginated = deptService.findPaginated(pageNo, pagseSize);
        DeptPage page = new DeptPage(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDept(@RequestParam(name = "v") String value, @RequestParam(name = "page") int pageNo) {
        int pagseSize = 10;
        Page<Dept> paginated = deptService.findAllWithFilters(value, pageNo, pagseSize);
        DeptPage page = new DeptPage(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Dept dept) {
        return ResponseEntity.ok(deptService.save(dept));
    }

    @GetMapping
    public ResponseEntity<?> getDepts() {
        return ResponseEntity.ok(deptService.getAll());
    }
}
