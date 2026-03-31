package com.jm.blogOnline.Controller;

import com.jm.blogOnline.Entity.Permission;
import com.jm.blogOnline.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog/permission")
@PreAuthorize("isAuthenticated()")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Permission>> getPermissions() {
        List<Permission> listPermission = permissionService.findAll();
        return new ResponseEntity<>(listPermission, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> getPermissionById( @PathVariable Long id) {
        Optional<Permission> permission = permissionService.findById(id);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> savePermission(@RequestBody Permission permission) {
        permission = permissionService.save(permission);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> updatePermission(@RequestBody Permission permission) {
        permissionService.update(permission);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        permissionService.deleteById(id);
        return new ResponseEntity<>("Permission deleted", HttpStatus.NO_CONTENT);
    }
}
