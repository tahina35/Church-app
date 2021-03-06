package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.SundayService;
import com.churchofphilippi.webserver.repository.SundayServiceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SundayServiceService implements BaseService<SundayService> {

    private SundayServiceRepository sundayServiceRepository;

    @Override
    public List<SundayService> getAll() {
        return sundayServiceRepository.findAll();
    }

    @Override
    public SundayService save(SundayService entity) {
        return sundayServiceRepository.save(entity);
    }

    @Override
    public void delete(SundayService entity) {
        sundayServiceRepository.delete(entity);
    }
}
