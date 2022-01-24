package com.example.springIt.service;

import com.example.springIt.domain.Role;
import com.example.springIt.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    private final RoleRepository roleRepository;

    public RolesService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

}
