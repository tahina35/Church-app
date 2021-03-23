package com.churchofphilippi.webserver.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {

    private final String fname;
    private final String lname;
    private final String email;
    private final char gender;
    private final String streetAddressLine1;
    private final String streetAddressLine2;
    private final String city;
    private final String state;
    private final int zipCode;
    private final String phoneNumber;

}
