package com.projectirp.IrpAuthenticationService.Services;

import com.projectirp.IrpAuthenticationService.Models.Users;
import com.projectirp.IrpAuthenticationService.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Users userRegistration(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    public Users findByEmail(String email) {
        return userRepo.findByEmail(email);
    }


}
