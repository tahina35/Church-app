package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.WednesdayService;
import com.churchofphilippi.webserver.repository.WednesdayServiceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WednesdayServiceService implements BaseService<WednesdayService> {

    private final WednesdayServiceRepository wednesdayServiceRepository;

    @Override
    public List<WednesdayService> getAll() {
        return wednesdayServiceRepository.findAll();
    }

    @Override
    public WednesdayService save(WednesdayService entity) {
        return wednesdayServiceRepository.save(entity);
    }

    @Override
    public void delete(WednesdayService entity) {
        wednesdayServiceRepository.delete(entity);
    }
}
