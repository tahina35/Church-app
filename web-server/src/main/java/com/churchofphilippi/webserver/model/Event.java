package com.churchofphilippi.webserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
            @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    @Column(
            name = "event_id",
            updatable = false
    )
    private Long eventId;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private boolean allDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Dept department;

}
