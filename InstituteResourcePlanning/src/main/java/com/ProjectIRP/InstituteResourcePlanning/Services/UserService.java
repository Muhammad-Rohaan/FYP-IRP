package com.ProjectIRP.InstituteResourcePlanning.Services;

import com.ProjectIRP.InstituteResourcePlanning.Models.Users;
import com.ProjectIRP.InstituteResourcePlanning.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public Users userRegistration(Users user) {
        return userRepo.save(user);
    }



}
