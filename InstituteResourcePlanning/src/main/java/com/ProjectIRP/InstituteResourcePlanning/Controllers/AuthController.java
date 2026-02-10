package com.ProjectIRP.InstituteResourcePlanning.Controllers;

import com.ProjectIRP.InstituteResourcePlanning.Models.Users;
import com.ProjectIRP.InstituteResourcePlanning.Services.UserService;
import com.ProjectIRP.InstituteResourcePlanning.Utilities.InstituteRoles;
import com.ProjectIRP.InstituteResourcePlanning.Utilities.JwtUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register-user")  ///  will be changed further
    public String registerUser(@RequestBody Users user) {
//        System.out.println(user.getRoles());
//        user.setRoles(InstituteRoles.valueOf(user.getRoles().toString().toUpperCase()));
        Users users = userService.userRegistration(user);
//        System.out.println("az/auth/register-user → "+users.getRoles());
//        System.out.println(jwtUtil.generateJwtToken(user));
        return "User Registered Successfully";
    }


    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users loginUser) {
        Users curUser = userService.findByEmail(loginUser.getEmail());
        if (curUser != null && BCrypt.checkpw(loginUser.getPassword(), curUser.getPassword())) {
            String jwtToken = jwtUtil.generateJwtToken(curUser);
            return ResponseEntity.status(200).body(jwtToken);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }



}
