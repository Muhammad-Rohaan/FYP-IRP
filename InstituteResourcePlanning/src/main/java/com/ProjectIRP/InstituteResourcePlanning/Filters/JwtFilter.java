package com.ProjectIRP.InstituteResourcePlanning.Filters;

import com.ProjectIRP.InstituteResourcePlanning.Services.IrpUserDetailsService;
import com.ProjectIRP.InstituteResourcePlanning.Utilities.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.getUsernameFromJwtToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = irpUserDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateJwtToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
