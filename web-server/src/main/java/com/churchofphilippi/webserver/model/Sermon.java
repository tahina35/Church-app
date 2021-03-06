package com.churchofphilippi.webserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sermon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sermon {

    @Id
    @SequenceGenerator(
            name = "sermon_sequence",
            sequenceName = "sermon_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sermon_sequence"
    )
    @Column(
            name = "sermon_id",
            updatable = false
    )
    private Long sermonId;

    @Column(nullable = false)
    private String mainVerse;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private Boolean isUploaded = false;

    public Sermon(String url, String thumbnail, String mainVerse) {
        this.mainVerse = mainVerse;
        this.url = url;
        this.thumbnail = thumbnail;
    }

}
