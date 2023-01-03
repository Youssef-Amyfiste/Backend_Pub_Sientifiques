package com.example.amyfec.security.dao;

import com.example.amyfec.security.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByAuthority(String authority);
}
