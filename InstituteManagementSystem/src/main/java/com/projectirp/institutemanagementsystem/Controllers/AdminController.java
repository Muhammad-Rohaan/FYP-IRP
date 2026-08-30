package com.projectirp.institutemanagementsystem.Controllers;


import com.projectirp.institutemanagementsystem.DTOs.TeacherRegistrationDto;
import com.projectirp.institutemanagementsystem.DTOs.TeacherResponseDTO;
import com.projectirp.institutemanagementsystem.Models.TeacherProfile;
import com.projectirp.institutemanagementsystem.Routes.AdminRoutes;
import com.projectirp.institutemanagementsystem.Services.AdminService;
import com.projectirp.institutemanagementsystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/admin")

public class AdminController extends AdminRoutes {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;


//    Users user = new Users();


    @Override
    @GetMapping("/dashboard") // test | Will be updated after all endpoints
    public ResponseEntity<String> dashboard() {
        System.out.println("adminService.dashboard()__" + adminService.dashboard());
        return ResponseEntity.status(200).body("Welcome " + adminService.dashboard());
    }


    @PostMapping("/register-teacher")
    public ResponseEntity<String> registerTeacher(@RequestBody TeacherRegistrationDto dto) {
        try {
            String curTeacher = adminService.registerTeacher(dto);
            return ResponseEntity.status(200).body(curTeacher);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body("ERROR in Register teacher " + e);
        }
    }

    @Override
    @GetMapping("/fetch-all-teachers")
    public ResponseEntity<List<TeacherProfile>> fetchAllTeachers() {
        try {
            List<TeacherProfile> allTeachers = adminService.fetchAllTeachers();
            return ResponseEntity.status(200).body(allTeachers);
        } catch (Exception e) {
            return (ResponseEntity<List<TeacherProfile>>) ResponseEntity.status(500);
        }
    }

    @Override
    @GetMapping("/fetch-teachers-by-class/{teacherClass}")
    public ResponseEntity<TeacherResponseDTO> fetchTeachersByClass(@PathVariable int teacherClass) {
        try {
            List<String> teachersByClass = adminService.fetchTeachersByClass(teacherClass);
            TeacherResponseDTO response = new TeacherResponseDTO(teachersByClass);
            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
//            return (ResponseEntity<List<TeacherProfile>>) ResponseEntity.status(500);
        }
    }

    @Override
    @GetMapping("/fetch-teachers-by-class-and-subject/{teacherClass}/{teacherSubject}")
    public ResponseEntity<TeacherResponseDTO> fetchTeachersByClassAndSubject(@PathVariable int teacherClass, @PathVariable String teacherSubject) {
        try {
            List<String> teachersByClassAndSubject = adminService.fetchTeachersByClassAndSubject(teacherClass, teacherSubject);
            TeacherResponseDTO response = new TeacherResponseDTO(teachersByClassAndSubject);
            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
//            return (ResponseEntity<List<TeacherProfile>>) ResponseEntity.status(500);
        }
    }

    @Override
    @PutMapping("/update-teacher/{teacherRegId}")
    public ResponseEntity<TeacherProfile> updateTeacher(@PathVariable String teacherRegId, @RequestBody TeacherProfile teacherProfile) {
        try {
            TeacherProfile response = adminService.updateTeacher(teacherRegId, teacherProfile);
            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
//            return (ResponseEntity<List<TeacherProfile>>) ResponseEntity.status(500);
        }
    }

    @Override
    @DeleteMapping("/delete-teacher/{teacherRegId}")
    public ResponseEntity<String> deleteTeacher(String teacherRegId) {
        try {
            String response = adminService.deleteTeacher(teacherRegId);
            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
//            return (ResponseEntity<List<TeacherProfile>>) ResponseEntity.status(500);
        }
    }


}

/*
* //    @PostMapping("/test")
//    public String test() {
//        return "Post Working.";
//    }


//    // Dashboard
//    @GetMapping("/dashboard")
//    public ResponseEntity<?> getDashboard() { }
//
//    // User Registration
//    @PostMapping("/register-user")
//    public ResponseEntity<?> registerUser(@RequestBody Users users) {
//        try {
//
//        } catch (Exception e) {
//
//        }
//    }
//
//    // Receptionist Routes
//    @PostMapping("/az-reception/register-receptionist")
//    public ResponseEntity<?> registerReceptionist(@RequestBody ReceptionistDTO dto) {
//        try {
//
//        } catch (Exception e) {
//
//        }
//    }
//
//    @GetMapping("/az-reception/fetch-all-receptionists")
//    public ResponseEntity<?> getAllReceptionists() {
//        try {
//
//        } catch (Exception e) {
//
//        }
//    }
//
//    @DeleteMapping("/az-reception/delete-receptionist/{receptionRegId}")
//    public ResponseEntity<?> deleteReceptionist(@PathVariable String receptionRegId) {
//        try {
//
//        } catch (Exception e) {
//
//        }
//    }
//
//    // Student Routes
//    @GetMapping("/az-teachers/getAllStudents")
//    public ResponseEntity<?> getAllStudents() {
//        try {
//
//        } catch (Exception e) {
//
//        }
//    }
//
//    // Teacher Routes
//    @PostMapping("/az-teachers/register-teacher")
//    public ResponseEntity<?> registerTeacher(@RequestBody TeacherDTO dto) {
//
//    }
//
//    @GetMapping("/az-teachers/fetch-all-teachers")
//    public ResponseEntity<?> getAllTeachers() { }
//
//    @GetMapping("/az-teachers/fetch-teachers-by-class/{className}")
//    public ResponseEntity<?> getTeachersByClass(@PathVariable String className) { }
//
//    @GetMapping("/az-teachers/fetch-teachers-by-class-and-subject/{className}/{subject}")
//    public ResponseEntity<?> searchTeachers(
//            @PathVariable String className,
//            @PathVariable String subject) { }
//
//    @PutMapping("/az-teachers/update-teacher/{teacherRegId}")
//    public ResponseEntity<?> updateTeacher(@PathVariable String teacherRegId, @RequestBody TeacherDTO dto) { }
//
//    @DeleteMapping("/az-teachers/delete-teacher/{teacherRegId}")
//    public ResponseEntity<?> deleteTeacher(@PathVariable String teacherRegId) { }
//
//    // Finances
//    @GetMapping("/finances")
//    public ResponseEntity<?> getFinances() { }
* */