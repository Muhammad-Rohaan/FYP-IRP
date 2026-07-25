package com.projectirp.institutemanagementsystem.Controllers;

import com.projectirp.institutemanagementsystem.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/az-coaching/home")
public class HomeController {

    @Autowired
    private UserRepo repo;

    @GetMapping("/")
    public String greet() {
        return "IMS Working";
    }



}
