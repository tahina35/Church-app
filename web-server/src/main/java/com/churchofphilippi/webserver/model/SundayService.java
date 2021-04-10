package com.churchofphilippi.webserver.model;

import com.churchofphilippi.webserver.model.keys.SundayServicekey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "sunday_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SundayServicekey.class)
public class SundayService {

    @Id
    @Column(nullable = false)
    private int serviceType;

    @Id
    @Column(nullable = false)
    private LocalDate date;

    @Column(
            nullable = false,
            length = 150
    )
    private String hymn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Member prayer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="sermon_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sermon sermon;

}
