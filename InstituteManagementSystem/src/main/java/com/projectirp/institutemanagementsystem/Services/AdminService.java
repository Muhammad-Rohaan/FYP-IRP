package com.projectirp.institutemanagementsystem.Services;

import com.projectirp.institutemanagementsystem.DTOs.TeacherRegistrationDto;
import com.projectirp.institutemanagementsystem.Models.TeacherProfile;
import com.projectirp.institutemanagementsystem.Models.Users;
import com.projectirp.institutemanagementsystem.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    

    public List<String> dashboard() {
        return userRepo.findByAdminName();
    }


    public void registerTeacher(TeacherRegistrationDto teacherRegistrationDto) {
        Users teacherUser = new Users(
                teacherRegistrationDto.getFullName(), teacherRegistrationDto.getContact(), teacherRegistrationDto.getEmail(), teacherRegistrationDto.getPassword(), teacherRegistrationDto.getRole(), teacherRegistrationDto.isActive()
        );

        userService.userRegistration(teacherUser);  // user has created but not the teacher profile...
        TeacherProfile teacherProfile = new TeacherProfile()

    }
}
