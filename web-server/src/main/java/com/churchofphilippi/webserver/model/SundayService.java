package com.churchofphilippi.webserver.model;

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
public class SundayService {

    @Id
    @SequenceGenerator(
            name = "sunday_service_sequence",
            sequenceName = "sunday_service_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sunday_service_sequence"
    )
    @Column(
            name = "sunday_service_id",
            updatable = false
    )
    private Long sundayServiceId;

    @Column(
            nullable = false,
            length = 150
    )
    private String worshipAndPraise;

    @Column(
            nullable = false,
            length = 150
    )
    private String confessionOfFaith;


    @Column(
            nullable = false,
            length = 150
    )
    private String hymn;

    @Column(
            nullable = false,
            length = 150
    )
    private String benediction;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sermon_id")
    private Sermon sermon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToMany
    @JoinTable(
            name = "sunday_service_news",
            joinColumns = @JoinColumn(name = "sunday_service_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id"))
    Set<News> news;

    public SundayService(String worshipAndPraise, String confessionOfFaith, String hymn, String benediction, Sermon sermon, LocalDate date, Member member) {
        this.worshipAndPraise = worshipAndPraise;
        this.confessionOfFaith = confessionOfFaith;
        this.hymn = hymn;
        this.benediction = benediction;
        this.sermon = sermon;
        this.date = date;
        this.member = member;
    }

    public SundayService(Long id, String worshipAndPraise, String confessionOfFaith, String hymn, String benediction, Sermon sermon, LocalDate date, Member member) {
        this.sundayServiceId = id;
        this.worshipAndPraise = worshipAndPraise;
        this.confessionOfFaith = confessionOfFaith;
        this.hymn = hymn;
        this.benediction = benediction;
        this.sermon = sermon;
        this.date = date;
        this.member = member;
    }

}
