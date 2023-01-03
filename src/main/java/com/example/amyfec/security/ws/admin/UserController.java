package com.example.amyfec.security.ws.admin;


import com.example.amyfec.security.bean.User;
import com.example.amyfec.security.service.facade.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminController")
@RequestMapping("/api/v1/admin")
public class UserController {
    @PutMapping("/")
    public int updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/")
    public int save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody User user) {
        return userService.signIn(user);
    }


    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }


    @GetMapping("/username/{username}")
    public UserDetails loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }


    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
