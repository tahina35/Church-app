package com.churchofphilippi.webserver.registration.emailPassword;

public interface EmailSender {

    void send(String to, String email);
}
