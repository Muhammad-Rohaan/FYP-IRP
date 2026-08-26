package com.projectirp.institutemanagementsystem.DTOs;

import com.projectirp.institutemanagementsystem.Utilities.InstituteRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime joiningDate;
    private List<String> subjects;
    private List<Integer> classes;
    private String address;
    private Integer age;

}