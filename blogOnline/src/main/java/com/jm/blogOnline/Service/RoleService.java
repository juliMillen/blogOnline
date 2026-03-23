package com.jm.blogOnline.Service;

import com.jm.blogOnline.Entity.Role;
import com.jm.blogOnline.Repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        if (roleRepository.findById(role.getIdRole()).isPresent()) {
            Role newRole = new Role();
            newRole.setIdRole(role.getIdRole());
            newRole.setRole(role.getRole());
            newRole.setPermissions(role.getPermissions());
            return roleRepository.save(newRole);
        }
        return null;
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
