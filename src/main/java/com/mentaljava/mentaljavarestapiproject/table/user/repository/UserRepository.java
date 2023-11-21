package com.mentaljava.mentaljavarestapiproject.table.user.repository;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userID);

    List<User> findAll();
}
