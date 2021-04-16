package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptRepository extends JpaRepository<Dept, Long>, JpaSpecificationExecutor<Dept> {

    //All depts that member does not belong to
    @Query(
            value = "select * from dept d where d.dept_id NOT IN (select dm.dept_id from dept_member dm where dm.member_id = ?1 AND dm.end_date IS NULL)",
            nativeQuery = true
    )
    List<Dept> findDeptsMemberNotBelongTo(Long id);

    List<Dept> findAllByParentDeptNull();

}
