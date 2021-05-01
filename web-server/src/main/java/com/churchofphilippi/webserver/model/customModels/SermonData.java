package com.churchofphilippi.webserver.model.customModels;

import com.churchofphilippi.webserver.model.Sermon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SermonData {
    private Sermon sermon;
    private LocalDate date;
}
