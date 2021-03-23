package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    Optional<Member> findByEmail(String email);

    @Override
    Optional<Member> findById(Long aLong);
}
