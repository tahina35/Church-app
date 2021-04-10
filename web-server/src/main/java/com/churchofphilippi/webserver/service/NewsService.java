package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.News;
import com.churchofphilippi.webserver.repository.NewsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class NewsService implements BaseService<News> {

    private final NewsRepository newsRepository;

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public News save(News entity) {
        return newsRepository.save(entity);
    }

    @Override
    public void delete(News entity) {
        newsRepository.delete(entity);
    }

    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    public List<News> getNews(int timeRange) {
        LocalDate today = LocalDate.now();
        List<News> res = new ArrayList<>();
        switch (timeRange) {
            case 1 -> {
                TemporalField fieldISO = WeekFields.of(Locale.getDefault()).dayOfWeek();
                LocalDate firstDayOfWeek = today.with(fieldISO, 1);
                res = newsRepository.getByDate(firstDayOfWeek);
            }
            case 2 -> {
                LocalDate lastWeekStart = today.minusDays(7 + today.getDayOfWeek().getValue()-1);
                LocalDate lastWeekEnd = today.minusDays(today.getDayOfWeek().getValue());
                res = newsRepository.getByDate(lastWeekStart, lastWeekEnd);
            }
            case 3 -> {
                LocalDate previousMonth = today.minusMonths(1);
                LocalDate monthStart = previousMonth.withDayOfMonth(1).plusDays(1);
                LocalDate monthEnd = previousMonth.withDayOfMonth(previousMonth.getMonth().maxLength()).plusDays(1);
                res = newsRepository.getByDate(monthStart, monthEnd);
            }
        }
        return res;
    }

}
