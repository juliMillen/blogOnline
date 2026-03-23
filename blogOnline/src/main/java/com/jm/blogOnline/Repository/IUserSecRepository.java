package com.jm.blogOnline.Repository;

import com.jm.blogOnline.Entity.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSecRepository extends JpaRepository<UserSec,Long> {

    Optional<UserSec> findByUsername(String username);
}
