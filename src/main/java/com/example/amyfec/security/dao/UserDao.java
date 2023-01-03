package com.example.amyfec.security.dao;

import com.example.amyfec.security.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
   // List<Demande> findByUserUsername(String username);
    User findByEmail(String email);
}
