package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
