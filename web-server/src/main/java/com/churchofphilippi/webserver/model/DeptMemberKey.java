package com.churchofphilippi.webserver.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class DeptMemberKey implements Serializable {

    Long memberId;
    Long deptId;
}
