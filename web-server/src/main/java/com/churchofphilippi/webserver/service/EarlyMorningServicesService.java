package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.EarlyMorningServices;
import com.churchofphilippi.webserver.repository.EarlyMorningServicesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EarlyMorningServicesService implements BaseService<EarlyMorningServices> {

    private final EarlyMorningServicesRepository earlyMorningServicesRepository;

    @Override
    public List<EarlyMorningServices> getAll() {
        return earlyMorningServicesRepository.findAll();
    }

    @Override
    public EarlyMorningServices save(EarlyMorningServices entity) {
        return earlyMorningServicesRepository.save(entity);
    }

    @Override
    public void delete(EarlyMorningServices entity) {
        earlyMorningServicesRepository.delete(entity);
    }
}
