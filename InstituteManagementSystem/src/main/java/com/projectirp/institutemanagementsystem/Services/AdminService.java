package com.projectirp.institutemanagementsystem.Services;

import com.projectirp.institutemanagementsystem.Models.Users;
import com.projectirp.institutemanagementsystem.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;


@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;
    

    public List<String> dashboard() {
        return userRepo.findByAdminName();
    }



}
