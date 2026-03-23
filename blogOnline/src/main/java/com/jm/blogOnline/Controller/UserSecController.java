package com.jm.blogOnline.Controller;

import com.jm.blogOnline.Entity.Role;
import com.jm.blogOnline.Entity.UserSec;
import com.jm.blogOnline.Service.RoleService;
import com.jm.blogOnline.Service.UserSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/blog/user")
public class UserSecController {

    @Autowired
    private UserSecService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public ResponseEntity<List<UserSec>> getAllUsers()
    {
        List<UserSec> listUsers = userService.findAll();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id)
    {
        Optional<UserSec> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec user)
    {
        Set<Role> roles = new HashSet<>();
        Role readRole;

        //encriptar contraseña
        user.setPassword(userService.encryptPassword(user.getPassword()));

        //recuperar la permission por su ID
        for(Role role: user.getRolList()){
            readRole = roleService.findById(role.getIdRole()).orElse(null);
            if(readRole != null){
                roles.add(readRole);
            }
        }
        if (!roles.isEmpty()){
            user.setRolList(roles);
            UserSec newUser = userService.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        return null;
    }

    @PatchMapping("/update")
    public ResponseEntity<UserSec> updateUserById(@RequestBody UserSec user)
    {
        UserSec updateUser = userService.findById(user.getIdUser()).orElse(null);
        if(updateUser != null){
            updateUser.setUsername(user.getUsername());
            updateUser.setPassword(user.getPassword());
            updateUser.setRolList(user.getRolList());
            userService.save(updateUser);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        userService.deleteById(id);
        return new ResponseEntity<>("User has been deleted", HttpStatus.NO_CONTENT);
    }
}
