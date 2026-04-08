package com.projectirp.IrpAuthenticationService.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reception")
public class ReceptionController {


    @GetMapping("/test")
    public String test() {
        return "Reception Working";
    }


}
