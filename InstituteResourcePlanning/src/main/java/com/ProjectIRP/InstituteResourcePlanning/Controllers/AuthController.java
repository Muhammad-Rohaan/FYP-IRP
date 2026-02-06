package com.ProjectIRP.InstituteResourcePlanning.Controllers;

import com.ProjectIRP.InstituteResourcePlanning.Models.Users;
import com.ProjectIRP.InstituteResourcePlanning.Services.UserService;
import com.ProjectIRP.InstituteResourcePlanning.Utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/az-coaching/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register-user")  ///  will be changed further
    public ResponseEntity<String> registerUser(@RequestBody Users user) {
        Users users = userService.userRegistration(user);
        System.out.println("az/auth/register-user → "+users.getRoles());
        System.out.println(jwtUtil.generateJwtToken(user));
        return ResponseEntity.ok("User Registered Successfully");
    }


    // Login
    



}
