package com.projectirp.institutemanagementsystem.Controllers;


import com.projectirp.institutemanagementsystem.DTOs.TeacherRegistrationDto;
import com.projectirp.institutemanagementsystem.Models.TeacherProfile;
import com.projectirp.institutemanagementsystem.Models.Users;
import com.projectirp.institutemanagementsystem.Routes.AdminRoutes;
import com.projectirp.institutemanagementsystem.Services.AdminService;
import com.projectirp.institutemanagementsystem.Services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Override
    @PostMapping("/register-teacher")
    public ResponseEntity<TeacherProfile> registerTeacher(TeacherRegistrationDto teacherRegistrationDto) {
        adminService.registerTeacher(teacherRegistrationDto);
        return null;
    }


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

}
