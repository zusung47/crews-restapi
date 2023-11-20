package com.mentaljava.mentaljavarestapiproject.table.user.repository;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userID);
}
