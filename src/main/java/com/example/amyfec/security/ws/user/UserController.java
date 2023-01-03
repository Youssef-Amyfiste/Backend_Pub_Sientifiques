package com.example.amyfec.security.ws.user;


import com.example.amyfec.security.bean.User;
import com.example.amyfec.security.service.facade.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @GetMapping("/getAuthority/username/{username}")
    public String
    getAuthority(@PathVariable String username) {
        return userService.getAuthority(username);
    }

    @PutMapping("/updatePassword/")
    public int updatePassword(@RequestBody User user) {
        return userService.updatePassword(user);
    }
    @PutMapping("/update/")
    public int update(@RequestBody User user) {
        return userService.update(user);
    }
    @PutMapping("/")
    public int updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    @PostMapping("/")
    public int save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/sign-in/")
    public String signIn(@RequestBody User user) {
        return userService.signIn(user);
    }


    @GetMapping("/username/{username}")
    public UserDetails loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }
    @GetMapping("/loadUserInfo/username/{username}")
    public UserDetails findByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.findByUsername(username);
    }
   /* @GetMapping("/findByUserUsername/username/{username}")
    public List<Demande> findByUserUsername(@PathVariable String username) {
        return userService.findByUserUsername(username);
    } */

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


}
