package com.jm.blogOnline.Controller;

import com.jm.blogOnline.Entity.Permission;
import com.jm.blogOnline.Entity.Role;
import com.jm.blogOnline.Service.PermissionService;
import com.jm.blogOnline.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/blog/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("")
    public ResponseEntity<List<Role>> getRoles()
    {
        List<Role> listRoles = roleService.findAll();
        return new ResponseEntity<>(listRoles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role>  getRoleById(@PathVariable Long id)
    {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role)
    {
        Set<Permission> permissionsList = new HashSet<>();
        Permission readPermission;

        //recuperar la permission por su ID
        for(Permission p : role.getPermissions()){
            readPermission = permissionService.findById(p.getIdPermission()).orElse(null);
            if(readPermission != null){
                permissionsList.add(readPermission);
            }
        }
        role.setPermissions(permissionsList);
        Role newRole = roleService.save(role);
        return new ResponseEntity<>(newRole, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Role>  updateRole(@RequestBody Role role)
    {
        Role updatedRole = roleService.findById(role.getIdRole()).orElse(null);
        roleService.update(updatedRole);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        roleService.deleteById(id);
        return new ResponseEntity<>("Role has been deleted", HttpStatus.NO_CONTENT);
    }
}
