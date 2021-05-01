package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.config.PageConfig;
import com.churchofphilippi.webserver.model.Member;
import com.churchofphilippi.webserver.model.Sermon;
import com.churchofphilippi.webserver.model.customModels.SermonData;
import com.churchofphilippi.webserver.model.pagination.CustomPage;
import com.churchofphilippi.webserver.service.SermonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/sermon")
public class SermonController {

    private final SermonService sermonService;
    private final PageConfig pageConfig;

    @GetMapping("/english/page/{pageNo}")
    public ResponseEntity<?> getEnglishSermon(@PathVariable("pageNo") int pageNo) {
        Page<SermonData> paginated = sermonService.findEnglishSermon(pageNo, pageConfig.getMobileSize());
        CustomPage<SermonData> page = new CustomPage<SermonData>(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/korean/page/{pageNo}")
    public ResponseEntity<?> getKoreanSermon(@PathVariable("pageNo") int pageNo) {
        Page<SermonData> paginated = sermonService.findKoreanSermon(pageNo, pageConfig.getMobileSize());
        CustomPage<SermonData> page = new CustomPage<SermonData>(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/wednesday/page/{pageNo}")
    public ResponseEntity<?> getWednesdaySermon(@PathVariable("pageNo") int pageNo) {
        Page<SermonData> paginated = sermonService.findWednesdaySermon(pageNo, pageConfig.getMobileSize());
        CustomPage<SermonData> page = new CustomPage<SermonData>(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/youth/page/{pageNo}")
    public ResponseEntity<?> getYouthSermon(@PathVariable("pageNo") int pageNo) {
        Page<SermonData> paginated = sermonService.findYouthSermon(pageNo, pageConfig.getMobileSize());
        CustomPage<SermonData> page = new CustomPage<SermonData>(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/morning/page/{pageNo}")
    public ResponseEntity<?> getMorningSermon(@PathVariable("pageNo") int pageNo) {
        Page<SermonData> paginated = sermonService.findMorningSermon(pageNo, pageConfig.getMobileSize());
        CustomPage<SermonData> page = new CustomPage<SermonData>(pageNo, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());
        return ResponseEntity.ok(page);
    }
}
