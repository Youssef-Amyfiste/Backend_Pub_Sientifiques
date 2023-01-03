package com.example.amyfec.security.service.impl;


import com.example.amyfec.security.bean.Role;
import com.example.amyfec.security.bean.User;
import com.example.amyfec.security.dao.UserDao;
import com.example.amyfec.security.service.facade.RoleService;
import com.example.amyfec.security.service.facade.UserService;
import com.example.amyfec.security.service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;
    @Autowired private RoleService roleService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
   // @Autowired private DemandeService demandeService;
    //private Demande demandeToPrint;
    private String str="@uca.ma";
    private int strSize=str.length();
    private int emailSize;
    private String subStr;
    private String subStr1="user";
    @Override
    public String signIn(User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("bad creditiel for username " + user.getUsername());
        }
        User loadUserByUsername = loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(loadUserByUsername);
        return token;
    }

    @Override
    public int save(User user) {
        User loadedUser = userDao.findByUsername(user.getEmail());
        if (loadedUser != null)
            return -1;
       /* if (userDao.findByEmail(user.getEmail()) != null) {
            return -2;
        } else if (!user.getEmail().matches("[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@(uca.ma|uca.ac.ma)")) {
            return -3;
        }*/
        //emailSize=user.getEmail().length();
        //subStr=user.getEmail().substring(emailSize-strSize);
        // System.out.println(subStr);
        // if(!subStr.equals(str))
        //  return -2;
        else {
            user.setUsername(user.getEmail());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAccountNonExpired(true);
            user.setCreatedAt(new Date());
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            user.setPasswordChanged(false);
            user.setAuthority("ROLE_CHERCHEUR");
            userDao.save(user);
            return 1;
        }
    }
    @Override
    public int updateUser(User user) {
        User loadedUser = userDao.findByUsername(user.getUsername());

        return 1;
    }
    @Override
    public int updatePassword(User user) {
        User loadedUser = userDao.findByUsername(user.getUsername());
        if (loadedUser == null)
            return -1;
        //System.out.println(user.getActualPassword());
        //System.out.println(loadedUser.getPassword());
       // if(passwordEncoder.encode(user.getActualPassword())!=loadedUser.getPassword())
          //  return -2;
        else {
            loadedUser.setPassword(passwordEncoder.encode(user.getNewPassword()));
            userDao.save(loadedUser);
            return 1;
        }
    }
    @Override
    public int update(User user) {
        User loadedUser = userDao.findByUsername(user.getUsername());
        if (loadedUser == null)
            return -1;
        else {
            //loadedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            loadedUser.setUsername(user.getUsername());
            loadedUser.setNom(user.getNom());
            loadedUser.setPrenom(user.getPrenom());
            loadedUser.setEmail(user.getEmail());
            loadedUser.setAuthority(user.getAuthority());
            userDao.save(loadedUser);
            return 1;
        }
    }
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null || user.getId() == null) {
            throw new UsernameNotFoundException("user " + username + " not founded");
        } else {
            return user;
        }
    }
   public String  getAuthority(String username){
       User user = findByUsername(username);
       return user.getAuthority();
   }
}
