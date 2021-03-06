package com.churchofphilippi.webserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dept")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    @Id
    @SequenceGenerator(
            name = "dept_sequence",
            sequenceName = "dept_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dept_sequence"
    )
    @Column(
            name = "dept_id",
            updatable = false
    )
    private Long deptId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_dept_id")
    private Dept parentDept;

    @Column(
            nullable = false,
            length = 50
    )
    private String name;

    public Dept(Dept parentDept, String name) {
        this.parentDept = parentDept;
        this.name = name;
    }

    public Dept(String name) {
        this.name = name;
    }
    
}
