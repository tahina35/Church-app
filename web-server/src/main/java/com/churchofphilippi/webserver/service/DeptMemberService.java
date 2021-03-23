package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.DeptMember;
import com.churchofphilippi.webserver.repository.DeptMemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeptMemberService implements BaseService<DeptMember> {

    private final DeptMemberRepository deptMemberRepository;

    public List<DeptMember> findByMemberId(Long id) {
        return deptMemberRepository.findDeptsByMemberId(id);
    }

    @Override
    public List<DeptMember> getAll() {
        return deptMemberRepository.findAll();
    }

    @Override
    public DeptMember save(DeptMember entity) {
        return deptMemberRepository.save(entity);
    }

    @Override
    public void delete(DeptMember entity) {
        deptMemberRepository.delete(entity);
    }
}
