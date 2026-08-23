package com.projectirp.institutemanagementsystem.DTOs;

import com.projectirp.institutemanagementsystem.Utilities.InstituteRoles;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TeacherRegistrationDto {
    // User fields
    private String fullName;
    private String email;
    private String contact;
    private String password;
    private InstituteRoles role;
    private boolean isActive;

    // TeacherProfile fields // teacherFullName
    private String teacherRegId;
    private String cnic;
    private String qualification;
    private Double salary;
    private LocalDate joiningDate;
    private List<String> subjects;
    private List<Integer> classes;
    private String address;
    private Integer age;

}