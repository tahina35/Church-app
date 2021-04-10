package com.churchofphilippi.webserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @SequenceGenerator(
            name = "notification_sequence",
            sequenceName = "notification_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_sequence"
    )
    @Column(
            name = "notification_id",
            updatable = false
    )
    private Long notificationId;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String content;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Dept department;

}
