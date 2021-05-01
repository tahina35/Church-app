package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.Member;
import com.churchofphilippi.webserver.model.Sermon;
import com.churchofphilippi.webserver.model.customModels.SermonData;
import com.churchofphilippi.webserver.repository.SermonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SermonService implements BaseService<Sermon> {

    private final SermonRepository sermonRepository;

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

    public Page<SermonData> findEnglishSermon(int pageNo, int pageSize) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return  sermonRepository.findEnglishSermon(pageable);
    }

    public Page<SermonData> findKoreanSermon(int pageNo, int pageSize) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return  sermonRepository.findKoreanSermon(pageable);
    }

    public Page<SermonData> findWednesdaySermon(int pageNo, int pageSize) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return  sermonRepository.findWednesdaySermon(pageable);
    }

    public Page<SermonData> findYouthSermon(int pageNo, int pageSize) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return  sermonRepository.findYouthSermon(pageable);
    }

    public Page<SermonData> findMorningSermon(int pageNo, int pageSize) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return  sermonRepository.findMorningSermon(pageable);
    }
}
