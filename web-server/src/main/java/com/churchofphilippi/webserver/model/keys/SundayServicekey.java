package com.churchofphilippi.webserver.model.keys;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SundayServicekey implements Serializable {

    private int serviceType;
    private LocalDate date;
}
