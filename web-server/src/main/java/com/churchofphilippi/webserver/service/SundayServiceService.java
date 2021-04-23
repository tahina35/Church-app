package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.SundayService;
import com.churchofphilippi.webserver.model.customModels.SundayServiceData;
import com.churchofphilippi.webserver.model.keys.SundayServicekey;
import com.churchofphilippi.webserver.repository.SundayServiceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SundayServiceService implements BaseService<SundayService> {

    private final SundayServiceRepository sundayServiceRepository;

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

    public Page<SundayService> findAllPaginated(int pageNo, int pageSize) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return sundayServiceRepository.findAll(pageable);
    }

    public SundayServiceData findLast() {
        LocalDate sunday = LocalDate.now().with(DayOfWeek.SUNDAY);
        SundayServiceData data = new SundayServiceData();
        data.setEnglishService(sundayServiceRepository.findLast(sunday, 2));
        data.setEnglishService(sundayServiceRepository.findLast(sunday, 1));
        return data;
    }

    public void deleteById(SundayServicekey id) {
        sundayServiceRepository.deleteById(id);
    }

}
