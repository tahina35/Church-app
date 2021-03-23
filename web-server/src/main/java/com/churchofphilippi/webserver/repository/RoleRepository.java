package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.Position;
import com.churchofphilippi.webserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.member.memberId = ?1")
    List<Role> findPositionsByMemberId(Long id);
}
