package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.WednesdayService;
import com.churchofphilippi.webserver.repository.WednesdayServiceRepository;
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

    public Page<WednesdayService> findAllPaginated(int pageNo, int pageSize) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return wednesdayServiceRepository.findAll(pageable);
    }

    @Transactional
    public void deleteByDate(LocalDate date) {
        wednesdayServiceRepository.deleteByDate(date);
    }

    public Optional<WednesdayService> findLast() {
        LocalDate sunday = LocalDate.now().with(DayOfWeek.SUNDAY);
        return wednesdayServiceRepository.findLast(sunday);
    }
}
