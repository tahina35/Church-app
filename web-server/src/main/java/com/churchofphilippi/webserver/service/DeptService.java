package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.repository.DeptRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DeptService implements BaseService<Dept>{

    private DeptRepository deptRepository;

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
}
