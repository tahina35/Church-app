package com.churchofphilippi.webserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @EmbeddedId
    private RoleKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(
            name = "member_id",
            nullable = false
    )
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("positionId")
    @JoinColumn(
            name = "position_id",
            nullable = false
    )
    private Position position;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String remark;

}