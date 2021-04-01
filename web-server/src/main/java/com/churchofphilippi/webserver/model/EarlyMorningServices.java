package com.churchofphilippi.webserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "early_morning_services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EarlyMorningServices {

    @Id
    @Column(nullable = false)
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="sermon_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sermon sermon;

}
