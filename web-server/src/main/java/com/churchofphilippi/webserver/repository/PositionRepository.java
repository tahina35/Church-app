package com.churchofphilippi.webserver.repository;

import com.churchofphilippi.webserver.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long>, JpaSpecificationExecutor<Position> {

    //All positions that member is not assigned to
    @Query(
            value = "select * from position p where p.position_id NOT IN (select r.position_id from  role r where r.member_id = ?1 AND r.end_date IS NULL)",
            nativeQuery = true
    )
    List<Position> findPositionsMemberNotAssignedTo(Long id);
}
