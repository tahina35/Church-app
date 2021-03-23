package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.model.Role;
import com.churchofphilippi.webserver.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService implements BaseService <Role> {

    private final RoleRepository roleRepository;

    public List<Role> findByMemberId(Long id) {
        return roleRepository.findPositionsByMemberId(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public void delete(Role entity) {
        roleRepository.delete(entity);
    }

}
