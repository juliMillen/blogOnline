package com.jm.blogOnline.Service;

import com.jm.blogOnline.Entity.UserSec;
import com.jm.blogOnline.Repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecService {

    @Autowired
    private IUserSecRepository userSecRepository;

    public List<UserSec> findAll() {
        return userSecRepository.findAll();
    }

    public Optional<UserSec> findById(Long id) {
        return userSecRepository.findById(id);
    }

    public UserSec save(UserSec user) {
        return userSecRepository.save(user);
    }

    public UserSec update(UserSec user) {
        if (userSecRepository.findById(user.getIdUser()).isPresent()) {
            UserSec newUser = new UserSec();
            newUser.setIdUser(user.getIdUser());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setEnable(user.isEnable());
            newUser.setAccountNonLocked(user.isAccountNonLocked());
            newUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
            newUser.setAccountNotExpired(user.isAccountNotExpired());
            newUser.setRolList(user.getRolList());
            userSecRepository.save(newUser);
        }
        return null;
    }

    public void deleteById(Long id) {
        userSecRepository.deleteById(id);
    }

    public String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
