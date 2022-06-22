package com.pgexmple.demo.service;



import com.pgexmple.demo.model.Role;
import com.pgexmple.demo.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public  User changeRole(Role newRole, String username);
    public User findByUsername(String username);
    public List<User> findAllUsers();


}
