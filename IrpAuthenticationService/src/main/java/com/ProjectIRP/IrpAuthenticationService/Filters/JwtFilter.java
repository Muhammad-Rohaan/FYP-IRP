package com.projectirp.IrpAuthenticationService.Filters;

import com.projectirp.IrpAuthenticationService.Services.IrpUserDetailsService;
import com.projectirp.IrpAuthenticationService.Utilities.JwtUtil;
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

//    public JwtFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
////        this.irpUserDetailsService = irpUserDetailsService;
//    }

    public JwtFilter(JwtUtil jwtUtil, IrpUserDetailsService irpUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.irpUserDetailsService = irpUserDetailsService;
    }


/// Working properly before Cookies implementation.
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            String userEmail = jwtUtil.getUserEmailFromJwtToken(token);
//
//            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = irpUserDetailsService.loadUserByUsername(userEmail);
//                System.out.println("doFilterInternal → userDetails.getAuthorities() "+userDetails.getAuthorities());
//
//                if (jwtUtil.validateJwtToken(token, userDetails)) {
////                    System.out.println("doFilterInternal → userDetails.getUsername() "+userDetails.getUsername());
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
////                    System.out.println("OK");
////                    System.out.println("AUTH HEADER = " + authHeader);
////                    System.out.println("TOKEN = " + token);
//                }
//            }
//        }
//        filterChain.doFilter(request, response);
//    }


///  for cookies implementation
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String token = getJwtTokenFromCookies(request);

    if (token != null) {
//        String token = authHeader.substring(7);
        String userEmail = jwtUtil.getUserEmailFromJwtToken(token);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = irpUserDetailsService.loadUserByUsername(userEmail);
//                System.out.println("doFilterInternal → userDetails.getAuthorities() "+userDetails.getAuthorities());

            if (jwtUtil.validateJwtToken(token, userDetails)) {
//                    System.out.println("doFilterInternal → userDetails.getUsername() "+userDetails.getUsername());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
//                    System.out.println("OK");
//                    System.out.println("AUTH HEADER = " + authHeader);
//                    System.out.println("TOKEN = " + token);
            }
        }
    }
    filterChain.doFilter(request, response);
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
