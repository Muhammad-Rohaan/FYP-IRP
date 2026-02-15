package com.ProjectIRP.InstituteResourcePlanning.Controllers;

import com.ProjectIRP.InstituteResourcePlanning.Models.Users;
import com.ProjectIRP.InstituteResourcePlanning.Services.CookiesService;
import com.ProjectIRP.InstituteResourcePlanning.Services.UserService;
import com.ProjectIRP.InstituteResourcePlanning.Utilities.InstituteRoles;
import com.ProjectIRP.InstituteResourcePlanning.Utilities.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
    private CookiesService cookiesService;

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
    public ResponseEntity<String> login(@RequestBody Users loginUser, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        Users curUser = userService.findByEmail(loginUser.getEmail());

        if (curUser != null && BCrypt.checkpw(loginUser.getPassword(), curUser.getPassword())) {
            String jwtToken = jwtUtil.generateJwtToken(curUser);   /// 14/2/25 | cookies

            ResponseCookie cookie = cookiesService.createJwtCookie(jwtToken);
            httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            System.out.println(curUser.getFullName());
            httpSession.setAttribute("username", curUser.getFullName());

            return ResponseEntity.status(200).body(jwtToken);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }



}
