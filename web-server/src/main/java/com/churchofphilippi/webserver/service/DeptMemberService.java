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
    private final FCMTokenService fcmTokenService;

    public List<DeptMember> findByMemberId(Long id) {
        return deptMemberRepository.findDeptsByMemberId(id);
    }

    @Override
    public List<DeptMember> getAll() {
        return deptMemberRepository.findAll();
    }

    @Override
    public DeptMember save(DeptMember entity) {
        DeptMember deptMember = deptMemberRepository.save(entity);
        Dept parent = deptMember.getDepartment().getParentDept();
        String topicName = fcmTokenService.getTopicFromDept(deptMember.getDepartment());
        fcmTokenService.subscribeToTopic(deptMember.getMember(), topicName);
        if(parent != null && parent.getDeptId() != null ) {
            topicName = fcmTokenService.getTopicFromDept(parent);
            fcmTokenService.subscribeToTopic(deptMember.getMember(), topicName);
        }
        return deptMember;
    }

    @Override
    public void delete(DeptMember deptMember) {
        deptMemberRepository.delete(deptMember);
        String topicName = fcmTokenService.getTopicFromDept(deptMember.getDepartment());
        fcmTokenService.unsubscribeFromTopic(deptMember.getMember(), topicName);
        Dept parent = deptMember.getDepartment().getParentDept();
        if(parent != null && parent.getDeptId() != null ) {
            topicName = fcmTokenService.getTopicFromDept(parent);
            fcmTokenService.unsubscribeFromTopic(deptMember.getMember(), topicName);
        }
    }
}
