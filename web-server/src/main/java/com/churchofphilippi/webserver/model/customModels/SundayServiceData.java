package com.churchofphilippi.webserver.model.customModels;

import com.churchofphilippi.webserver.model.SundayService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SundayServiceData {

    private SundayService englishService;
    private SundayService koreanService;
}
