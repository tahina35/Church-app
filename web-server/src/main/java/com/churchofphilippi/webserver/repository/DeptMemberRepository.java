package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.DeptMember;
import com.churchofphilippi.webserver.model.DeptMemberKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptMemberRepository extends JpaRepository<DeptMember, DeptMemberKey> {

    @Query("SELECT dm FROM DeptMember dm WHERE dm.member.memberId = ?1")
    List<DeptMember> findDeptsByMemberId(Long id);
}
