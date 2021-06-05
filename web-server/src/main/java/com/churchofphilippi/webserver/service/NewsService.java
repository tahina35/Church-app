package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.config.MobileConfig;
import com.churchofphilippi.webserver.model.News;
import com.churchofphilippi.webserver.repository.NewsRepository;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.AllArgsConstructor;

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
    private final FCMTokenService tokenService;
    private final MobileConfig mobileConfig;

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public News save(News entity) {
        News news = newsRepository.save(entity);
        String topic = tokenService.setTopic(news.getDepartment());
        String content = news.getContent().replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        content = (content.length() > 150) ? content.substring(0,150) + "..." : content;
        String title = "News";
        String body = content;
        tokenService.sendNotification(title, body, topic);
        return news;
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
            case 1: {
                TemporalField fieldISO = WeekFields.of(Locale.getDefault()).dayOfWeek();
                LocalDate firstDayOfWeek = today.with(fieldISO, 1);
                res = newsRepository.getByDate(firstDayOfWeek);
                break;
            }
            case 2: {
                LocalDate lastWeekStart = today.minusDays(7 + today.getDayOfWeek().getValue()-1);
                LocalDate lastWeekEnd = today.minusDays(today.getDayOfWeek().getValue());
                res = newsRepository.getByDate(lastWeekStart, lastWeekEnd);
                break;
            }
            case 3: {
                LocalDate previousMonth = today.minusMonths(1);
                LocalDate monthStart = previousMonth.withDayOfMonth(1).plusDays(1);
                LocalDate monthEnd = previousMonth.withDayOfMonth(previousMonth.getMonth().maxLength()).plusDays(1);
                res = newsRepository.getByDate(monthStart, monthEnd);
                break;
            }
        }
        return res;
    }

    public List<News> getNews() {
        LocalDate today = LocalDate.now();
        return newsRepository.getNews(today);
    }

}
