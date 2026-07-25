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
    private JwtFilter jwtFilter;
    @Autowired
    private JwtUtil jwtUtil;


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {  /// this will allow not to use default spring-security filters instead of that use this one(filter)
//        return httpSecurity
//                .csrf(customizer -> customizer.disable())
//                .authorizeHttpRequests(request ->
//                        request
//                                .requestMatchers("/login", "/register").permitAll()  // Open for all
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/reception/**").hasRole("RECEPTIONIST")
//                                .requestMatchers("/teacher/**").hasRole("TEACHER")
//                                .requestMatchers("/student/**").hasRole("STUDENT")
//                                .anyRequest().authenticated()
//                                .and()
//                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpSecurity.build();
//    }

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
         http.addFilterBefore(new JwtFilter(jwtUtil, irpUserDetailsService) /*jwtFilter*/ , UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }


//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        return daoAuthenticationProvider;
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new IrpUserDetailsService();
    }



}
