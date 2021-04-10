package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.exception.exceptionModel.ForeignKeyConstraintException;
import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.specification.DeptSpecification;
import com.churchofphilippi.webserver.repository.DeptRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeptService implements BaseService<Dept>{

    private final DeptRepository deptRepository;

    @Override
    public List<Dept> getAll() {
        return deptRepository.findAll();
    }

    @Override
    public Dept save(Dept entity) {
        return deptRepository.save(entity);
    }

    @Override
    public void delete(Dept entity) {
        deptRepository.delete(entity);
    }

    public void deleteById(Long id) {
        try {
            deptRepository.deleteById(id);
        } catch (Exception e) {
            throw new ForeignKeyConstraintException("Some members still belong to this department. Please remove them before proceeding to this action.");
        }

    }

    public Page<Dept> findPaginated(int pageNo, int pageSize) {
        Sort sort = Sort.by("deptId").ascending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return deptRepository.findAll(pageable);
    }

    public Page<Dept> findAllWithFilters(String searchValue, int pageNo, int pageSize) {
        Sort sort = Sort.by("deptId").ascending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        DeptSpecification specification = new DeptSpecification(searchValue);
        return deptRepository.findAll(specification, pageable);
    }

    public List<Dept> findDeptsMemberNotBelongTo(Long id) {
        return deptRepository.findDeptsMemberNotBelongTo(id);
    }

}
