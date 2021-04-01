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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="sermon_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sermon sermon;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "sunday_service_news",
            joinColumns = {
                    @JoinColumn(name = "date"),
                    @JoinColumn(name = "service_type"),
            },
            inverseJoinColumns = @JoinColumn(name = "news_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Set<News> news;

}
