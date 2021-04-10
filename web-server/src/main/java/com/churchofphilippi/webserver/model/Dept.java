package com.churchofphilippi.webserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dept")
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name="parent_dept_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Dept parentDept;

    @Column(
            nullable = false,
            length = 50
    )
    private String name;

    @Column(nullable = false)
    private LocalDate creationDate;

    public Dept(String name, LocalDate creationDate, Dept parentDept) {
        this.parentDept = parentDept;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Dept(String name) {
        this.name = name;
    }
    
}
