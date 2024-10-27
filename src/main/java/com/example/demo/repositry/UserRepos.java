package com.example.demo.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.security.Entity.User;

@Repository
public interface UserRepos extends JpaRepository<User, String> {
  User findByUsername(String username);
}
