package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.Sermon;
import com.churchofphilippi.webserver.repository.SermonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SermonService implements BaseService<Sermon> {

    private SermonRepository sermonRepository;

    @Override
    public List<Sermon> getAll() {
        return sermonRepository.findAll();
    }

    @Override
    public Sermon save(Sermon entity) {
        return sermonRepository.save(entity);
    }

    @Override
    public void delete(Sermon entity) {
        sermonRepository.delete(entity);
    }
}
