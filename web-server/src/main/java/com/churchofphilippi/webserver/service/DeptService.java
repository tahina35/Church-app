package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.config.PageConfig;
import com.churchofphilippi.webserver.exception.exceptionModel.ForeignKeyConstraintException;
import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.Member;
import com.churchofphilippi.webserver.model.customModels.DepartmentData;
import com.churchofphilippi.webserver.model.pagination.CustomPage;
import com.churchofphilippi.webserver.model.specification.DeptSpecification;
import com.churchofphilippi.webserver.repository.DeptRepository;
import com.churchofphilippi.webserver.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DeptService implements BaseService<Dept>{

    private final DeptRepository deptRepository;
    private final MemberRepository memberRepository;
    private final PageConfig pageConfig;

    @Override
    public List<Dept> getAll() {
        return deptRepository.findAll();
    }

    @Override
    public Dept save(Dept entity) {
        return deptRepository.save(entity);
    }

    @Override
    public void delete(Dept entity) {
        deptRepository.delete(entity);
    }

    public void deleteById(Long id) {
        try {
            deptRepository.deleteById(id);
        } catch (Exception e) {
            throw new ForeignKeyConstraintException("Some members still belong to this department. Please remove them before proceeding to this action.");
        }
    }

    public Page<Dept> findPaginated(int pageNo, int pageSize) {
        Sort sort = Sort.by("deptId").ascending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return deptRepository.findAll(pageable);
    }

    public Page<Dept> findAllWithFilters(String searchValue, int pageNo, int pageSize) {
        Sort sort = Sort.by("deptId").ascending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        DeptSpecification specification = new DeptSpecification(searchValue);
        return deptRepository.findAll(specification, pageable);
    }

    public List<Dept> findDeptsMemberNotBelongTo(Long id) {
        return deptRepository.findDeptsMemberNotBelongTo(id);
    }

    public List<Dept> findParentDept() {
        return deptRepository.findParentDept();
    }

    public DepartmentData getMemberDeptData(Long id) {
        DepartmentData data = new DepartmentData();
        List<Dept> depts = deptRepository.findByMember(id);

        if(!depts.isEmpty()) {
            depts.addAll(getSubDepts(id, depts));
            depts = new ArrayList<Dept>(new HashSet<Dept>(depts));
            Sort sort = Sort.by("fname").ascending();
            Pageable pageable = PageRequest.of(0, pageConfig.getMobileSize(), sort);
            Page<Member> paginated = memberRepository.findByDept(depts.get(0).getDeptId(), pageable);
            CustomPage<Member> members = new CustomPage<Member>(0, paginated.getTotalPages(), paginated.getTotalElements(), paginated.getContent());

            data.setDepts(depts);
            data.setMembers(members);
        } else {
            return null;
        }

        return data;
    }

    public List<Dept> getSubDepts(Long memberId, List<Dept> depts) {
        List<Dept> res = new ArrayList<Dept>();
        for(Dept d : depts) {
            if(d.getLeader() != null && d.getLeader().getMemberId() == memberId) {
                res.addAll(deptRepository.findSubDepts(d.getDeptId()));
            }
        }
        return res;
    }
}
