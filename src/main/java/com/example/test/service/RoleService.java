package com.example.test.service;
import com.example.test.entity.Role;
import com.example.test.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Page<Role> getAll(Integer page, Integer size) {
        return roleRepository.findAll(PageRequest.of(page, size));
    }

    public Role getReferenceById(Integer id) {
        return roleRepository.getById(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> getById(Integer id) {
        return roleRepository.findById(id);
    }

    public void update(Role role) {
        roleRepository.save(role);
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }



}
