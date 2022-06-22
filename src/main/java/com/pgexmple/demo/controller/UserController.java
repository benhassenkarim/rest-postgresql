package com.pgexmple.demo.controller;


import com.pgexmple.demo.model.Role;
import com.pgexmple.demo.model.User;
import com.pgexmple.demo.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("reg")

    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername())!=null){
            return  new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<JSONObject> createUser( @RequestBody User user ) {

        JSONObject responseJson = new JSONObject();

        if (userService.findByUsername(user.getUsername())!=null) {

            responseJson.put("status", "User with that username already exists.");

            return new ResponseEntity<JSONObject>(responseJson, HttpStatus.BAD_REQUEST);
        }

        responseJson.put("status", "User created.");

        return new ResponseEntity<JSONObject>(responseJson, HttpStatus.OK);
    }

    @GetMapping("login")
    public ResponseEntity<?> loginAndLogout(HttpServletRequest request){
        Principal principal=request.getUserPrincipal();
        if(principal==null||principal.getName()==null){
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        User user=userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping("change/{role}")
    public ResponseEntity<?> changeRole(Principal principal,@PathVariable Role role){
        User user=userService.changeRole(role,principal.getName());
        return  ResponseEntity.ok(user);
    }
}
