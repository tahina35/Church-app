package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.EarlyMorningServices;
import com.churchofphilippi.webserver.repository.EarlyMorningServicesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    public Page<EarlyMorningServices> findAllPaginated(int pageNo, int pageSize) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return earlyMorningServicesRepository.findAll(pageable);
    }

    @Transactional
    public void deleteByDate(LocalDate date) {
        earlyMorningServicesRepository.deleteByDate(date);
    }

}
