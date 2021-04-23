package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.config.PageConfig;
import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.pagination.CustomPage;
import com.churchofphilippi.webserver.service.DeptService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/department")
public class DepartmentController {

    private final DeptService deptService;
    private final PageConfig pageConfig;

    @GetMapping("/page/{pageNo}")
    public ResponseEntity<?> getDeptssPaginated(@PathVariable("pageNo")int pageNo) {
        Page<Dept> paginated = deptService.findPaginated(pageNo, pageConfig.getSize());
        CustomPage<Dept> page = new CustomPage<Dept>(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDept(@RequestParam(name = "v") String value, @RequestParam(name = "page") int pageNo) {
        Page<Dept> paginated = deptService.findAllWithFilters(value, pageNo, pageConfig.getSize());
        CustomPage<Dept> page = new CustomPage<Dept>(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
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

    @GetMapping("/parent-dept")
    public ResponseEntity<?> getDeptsParentDept() {
        return ResponseEntity.ok(deptService.findParentDept());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        deptService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<?> getMemberDeptData(@PathVariable("id")Long id) {
        return ResponseEntity.ok(deptService.getMemberDeptData(id));
    }

}
