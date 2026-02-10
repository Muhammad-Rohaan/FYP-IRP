package com.ProjectIRP.InstituteResourcePlanning.Controllers;

import com.ProjectIRP.InstituteResourcePlanning.Repositories.UserRepo;
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
