package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.YouthService;
import com.churchofphilippi.webserver.repository.YouthRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class YouthServiceService implements BaseService<YouthService> {

    private final YouthRepository youthRepository;

    @Override
    public List<YouthService> getAll() {
        return youthRepository.findAll();
    }

    @Override
    public YouthService save(YouthService entity) {
        return youthRepository.save(entity);
    }

    @Override
    public void delete(YouthService entity) {
        youthRepository.delete(entity);
    }

    public Page<YouthService> findAllPaginated(int pageNo, int pageSize) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return youthRepository.findAll(pageable);
    }

    @Transactional
    public void deleteByDate(LocalDate date) {
        youthRepository.deleteByDate(date);
    }


    public Optional<YouthService> findLast() {
        LocalDate sunday = LocalDate.now().with(DayOfWeek.SUNDAY);
        return youthRepository.findLast(sunday);
    }
}
