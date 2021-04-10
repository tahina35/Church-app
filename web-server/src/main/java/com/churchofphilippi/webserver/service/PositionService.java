package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.exception.exceptionModel.ForeignKeyConstraintException;
import com.churchofphilippi.webserver.model.Position;
import com.churchofphilippi.webserver.model.specification.PositionSpecification;
import com.churchofphilippi.webserver.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionService implements BaseService<Position> {

    private final PositionRepository positionRepository;

    public Page<Position> findAllWithFilters(String searchValue, int pageNo, int pageSize) {
        Sort sort = Sort.by("positionId").ascending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        PositionSpecification specification = new PositionSpecification(searchValue);
        return positionRepository.findAll(specification, pageable);
    }

    public Page<Position> findPaginated(int pageNo, int pageSize) {
        Sort sort = Sort.by("positionId").ascending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return positionRepository.findAll(pageable);
    }

    public List<Position> findPositionsMemberNotAssignedTo(Long id) {
        return positionRepository.findPositionsMemberNotAssignedTo(id);
    }

    public void deleteById(Long id) {
        try {
            positionRepository.deleteById(id);
        } catch (Exception e) {
            throw new ForeignKeyConstraintException("Some members are still assigned to this position. Please remove them before proceeding to this action.");
        }

    }

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
