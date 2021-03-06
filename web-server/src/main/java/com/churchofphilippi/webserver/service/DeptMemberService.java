package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.DeptMember;
import com.churchofphilippi.webserver.repository.DeptMemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DeptMemberService implements BaseService<DeptMember> {

    private DeptMemberRepository deptMemberRepository;

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
