package com.ProjectIRP.InstituteResourcePlanning.Controllers;


import com.ProjectIRP.InstituteResourcePlanning.Models.Users;
import com.ProjectIRP.InstituteResourcePlanning.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


//    @PostMapping("/register-user")
//    public Users registerUser(@RequestBody Users user) {
//        return userService.userRegistration(user);
//    }


}
