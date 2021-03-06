package com.churchofphilippi.webserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleKey implements Serializable {

    @Column(
            name = "member_id",
            nullable = false
    )
    Long memberId;

    @Column(
            name = "position_id",
            nullable = false
    )
    Long positionId;
}
