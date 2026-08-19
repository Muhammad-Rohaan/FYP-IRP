# JWT Authentication Code

## `JwtUtil.java`
```java
package com.projectirp.institutemanagementsystem.Utilities;

import com.projectirp.institutemanagementsystem.Models.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;


    public String generateJwtToken(Users user) {

        Map<String, Object> userClaims = new HashMap<>();
        userClaims.put("role", user.getRoles().name());

        String jwtToken = Jwts.builder()
                .setClaims(userClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .compact();

        return jwtToken;
    }

    public boolean validateJwtToken(String jwtToken, UserDetails userDetails) {

        boolean flag = false;
        String username = getUserEmailFromJwtToken(jwtToken);

        if (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken)) {
            flag = true;
        }
        return flag;
    }

    private boolean isTokenExpired(String jwtToken) {
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getExpiration();
        return expirationDate.before(new Date());
    }

    public String getRolesFromJwtToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .get("role", String.class);
    }

    public String getUserEmailFromJwtToken(String jwtToken) {
        String userEmail = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();

        return userEmail;
    }
}
```

## `JwtFilter.java`
```java
package com.projectirp.institutemanagementsystem.Filters;

import com.projectirp.institutemanagementsystem.Services.IrpUserDetailsService;
import com.projectirp.institutemanagementsystem.Utilities.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private IrpUserDetailsService irpUserDetailsService;


    public JwtFilter(JwtUtil jwtUtil, IrpUserDetailsService irpUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.irpUserDetailsService = irpUserDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/") || path.startsWith("/auth/**") || path.equals("/auth/register-user/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJwtTokenFromCookies(request);

            if (token != null) {
                String userEmail = jwtUtil.getUserEmailFromJwtToken(token);

                if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = irpUserDetailsService.loadUserByUsername(userEmail);

                    if (jwtUtil.validateJwtToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println("Could not set user authentication from cookie: " + e.getMessage());
        }
    }

    public String getJwtTokenFromCookies(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getCookies() == null) {
            return null;
        }

        for (Cookie cookie: httpServletRequest.getCookies()) {
            if ("jwt_token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
```

## `CookiesService.java`
```java
package com.projectirp.institutemanagementsystem.Services;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class CookiesService {

    public ResponseCookie createJwtCookie(String jwtToken) {
        return ResponseCookie.from("jwt_token", jwtToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // for 7 days
                .sameSite("Strict")
                .build();
    }

    public ResponseCookie deleteJwtCookie() {
        return ResponseCookie.from("jwt_token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
    }

}
```

## `AuthController.java` (Authentication Endpoints)
```java
package com.projectirp.institutemanagementsystem.Controllers;

import com.projectirp.institutemanagementsystem.Models.Users;
import com.projectirp.institutemanagementsystem.Services.CookiesService;
import com.projectirp.institutemanagementsystem.Services.UserService;
import com.projectirp.institutemanagementsystem.Utilities.InstituteRoles;
import com.projectirp.institutemanagementsystem.Utilities.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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


    @PostMapping("/register-user")
    public String registerUser(@RequestBody Users user) {
        user.setRoles(InstituteRoles.valueOf(user.getRoles().toString().toUpperCase()));
        Users users = userService.userRegistration(user);
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users loginUser, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        Users curUser = userService.findByEmail(loginUser.getEmail());

        if (curUser != null && BCrypt.checkpw(loginUser.getPassword(), curUser.getPassword())) {
            String jwtToken = jwtUtil.generateJwtToken(curUser);

            ResponseCookie cookie = cookiesService.createJwtCookie(jwtToken);
            httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            httpSession.setAttribute("username", curUser.getFullName());

            return ResponseEntity.status(200).body(jwtToken);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
```

## `SecurityConfig.java`
```java
package com.projectirp.institutemanagementsystem.Config;

import com.projectirp.institutemanagementsystem.Filters.JwtFilter;
import com.projectirp.institutemanagementsystem.Services.IrpUserDetailsService;
import com.projectirp.institutemanagementsystem.Utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private IrpUserDetailsService irpUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/auth/register-user", "/").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/reception/**").hasRole("RECEPTIONIST")
                        .requestMatchers("/teacher/**").hasRole("TEACHER")
                        .requestMatchers("/parent/**").hasRole("PARENT")
                        .requestMatchers("/student/**").hasRole("STUDENT")
                        .anyRequest().authenticated()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // JWT filter
         http.addFilterBefore(new JwtFilter(jwtUtil, irpUserDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new IrpUserDetailsService();
    }

}
```
