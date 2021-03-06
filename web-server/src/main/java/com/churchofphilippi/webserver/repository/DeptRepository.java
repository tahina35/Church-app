package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Long> {
}
