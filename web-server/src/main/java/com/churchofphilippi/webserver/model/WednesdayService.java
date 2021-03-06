package com.churchofphilippi.webserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wednesday_service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WednesdayService {

    @Id
    @SequenceGenerator(
            name = "wednesday_service_sequence",
            sequenceName = "wednesday_service_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wednesday_service_sequence"
    )
    @Column(
            name = "wednesday_service_id",
            updatable = false
    )
    private Long wednesdayServiceId;

    @Column(
            nullable = false,
            length = 150
    )
    private String worshipAndPraise;

    @Column(
            nullable = false,
            length = 150
    )
    private String hymn;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sermon_id")
    private Sermon sermon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public WednesdayService(String worshipAndPraise, String hymn, Sermon sermon, LocalDate date, Member member) {
        this.worshipAndPraise = worshipAndPraise;
        this.hymn = hymn;
        this.sermon = sermon;
        this.date = date;
        this.member = member;
    }
    
}
