package com.churchofphilippi.webserver.model;

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
    @SequenceGenerator(
            name = "morning_service_sequence",
            sequenceName = "morning_service_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "morning_service_sequence"
    )
    @Column(
            name = "morning_service_id",
            updatable = false
    )
    private Long morningServiceId;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sermon_id")
    private Sermon sermon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public EarlyMorningServices(Sermon sermon, LocalDate date, Member member) {
        this.sermon = sermon;
        this.date = date;
        this.member = member;
    }

}
