package com.projectirp.institutemanagementsystem.Routes;

import com.projectirp.institutemanagementsystem.Models.Users;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;


public abstract class AuthRoutes {

    public abstract ResponseEntity<String> registerUser(Users user);
    public abstract ResponseEntity<String> login(Users loginUser, HttpServletResponse httpServletResponse, HttpSession httpSession);
}
