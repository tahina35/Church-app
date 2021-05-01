package com.churchofphilippi.webserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
public class Member implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_sequence"
    )
    private Long memberId;

    @Column(
            nullable = false,
            length = 100
    )
    private String fname;

    @Column(
            nullable = false,
            length = 100
    )
    private String lname;

    @Column(nullable = false)
    private char gender;

    @Column(nullable = false)
    private LocalDate memberDate;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(length = 150)
    private String streetAddressLine1;

    @Column(length = 150)
    private String streetAddressLine2;

    @Column(length = 50)
    private String city;

    @Column(length = 2)
    private String state;

    private int zipCode;

    @Column(
            unique = true
    )
    private String email;

    @Column(length = 10)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean admin = false;

    @Column(nullable = false)
    private String password;

    private String accessToken;

    public Member(String fname, String lname, String email, String password, char gender, LocalDate memberDate, String streetAddressLine1, String streetAddressLine2, String city, String state, int zipCode, String phoneNumber) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.memberDate = memberDate;
        this.streetAddressLine1 = streetAddressLine1;
        this.streetAddressLine2 = streetAddressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
