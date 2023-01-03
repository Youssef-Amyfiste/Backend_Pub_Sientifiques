package com.example.amyfec.security.service.facade;

import com.example.amyfec.security.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    String signIn(User user);
    int save(User user);
    List<User> findAll();
    int updatePassword(User user);
    public int updateUser(User user);
    public int update(User user);
    public User findByUsername(String username);
    User findByEmail(String email);
    //public Demande getDemandeToPrint();
   // List<Demande> findByUserUsername(String username);
   public String getAuthority(String username);
}
