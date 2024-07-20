package com.vanlang.shopdongho.repository;

import com.vanlang.shopdongho.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
