package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.FCMToken;
import com.churchofphilippi.webserver.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FCMTokenRepository extends JpaRepository<FCMToken, Long> {

    FCMToken findByMemberAndDeviceId(Member m, String deviceId);

    FCMToken findByMember(Member member);
}
