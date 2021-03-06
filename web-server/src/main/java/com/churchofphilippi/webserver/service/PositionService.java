package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.Position;
import com.churchofphilippi.webserver.repository.PositionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PositionService implements BaseService<Position> {

    private PositionRepository positionRepository;

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position save(Position entity) {
        return positionRepository.save(entity);
    }

    @Override
    public void delete(Position entity) {
        positionRepository.delete(entity);
    }
}
