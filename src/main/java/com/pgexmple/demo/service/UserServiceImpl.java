package com.pgexmple.demo.service;


import com.pgexmple.demo.model.Role;
import com.pgexmple.demo.model.User;
import com.pgexmple.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        user.setRole(Role.ROLE_USER);
        return  userRepo.save(user);
    }
    @Override
    public  User changeRole(Role newRole, String username){
        User user =userRepo.findByUsername(username).orElseThrow(()-> new RuntimeException());
        user.setRole(newRole);
        return  userRepo.save(user);
    }
    @Override
    public User findByUsername(String username){
        return userRepo.findByUsername(username).orElse(null);
    }
    @Override
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }
}
