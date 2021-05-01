package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Member;
import com.churchofphilippi.webserver.model.Sermon;
import com.churchofphilippi.webserver.model.customModels.SermonData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SermonRepository extends JpaRepository<Sermon, Long> {

    @Query(value = "SELECT new com.churchofphilippi.webserver.model.customModels.SermonData(s, ss.date) FROM SundayService ss INNER JOIN Sermon s ON ss.sermon.sermonId = s.sermonId WHERE ss.serviceType = 2 AND s.isUploaded = true")
    Page<SermonData> findEnglishSermon(Pageable pageable);

    @Query(value = "SELECT new com.churchofphilippi.webserver.model.customModels.SermonData(s, ss.date) FROM SundayService ss INNER JOIN Sermon s ON ss.sermon.sermonId = s.sermonId WHERE ss.serviceType = 1 AND s.isUploaded = true")
    Page<SermonData> findKoreanSermon(Pageable pageable);

    @Query(value = "SELECT new com.churchofphilippi.webserver.model.customModels.SermonData(s, ws.date) FROM WednesdayService ws INNER JOIN Sermon s ON ws.sermon.sermonId = s.sermonId WHERE s.isUploaded = true")
    Page<SermonData> findWednesdaySermon(Pageable pageable);

    @Query(value = "SELECT new com.churchofphilippi.webserver.model.customModels.SermonData(s, ys.date) FROM YouthService ys INNER JOIN Sermon s ON ys.sermon.sermonId = s.sermonId WHERE s.isUploaded = true")
    Page<SermonData> findYouthSermon(Pageable pageable);

    @Query(value = "SELECT new com.churchofphilippi.webserver.model.customModels.SermonData(s, ems.date) FROM EarlyMorningServices ems INNER JOIN Sermon s ON ems.sermon.sermonId = s.sermonId WHERE s.isUploaded = true")
    Page<SermonData> findMorningSermon(Pageable pageable);
}
