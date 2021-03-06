package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.DeptMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptMemberRepository extends JpaRepository<DeptMember, Long> {
}
