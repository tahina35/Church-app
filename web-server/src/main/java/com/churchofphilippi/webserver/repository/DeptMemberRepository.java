package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.DeptMember;
import com.churchofphilippi.webserver.model.keys.DeptMemberKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeptMemberRepository extends JpaRepository<DeptMember, DeptMemberKey> {

    @Query("SELECT dm FROM DeptMember dm WHERE dm.member.memberId = ?1 ORDER BY dm.startDate DESC")
    List<DeptMember> findDeptsByMemberId(Long id);

    @Override
    Optional<DeptMember> findById(DeptMemberKey deptMemberKey);
}
