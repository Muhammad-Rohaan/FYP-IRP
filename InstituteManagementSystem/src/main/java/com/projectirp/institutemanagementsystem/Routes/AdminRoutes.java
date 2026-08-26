package com.projectirp.institutemanagementsystem.Routes;

import com.projectirp.institutemanagementsystem.DTOs.TeacherRegistrationDto;
import com.projectirp.institutemanagementsystem.DTOs.TeacherResponseDTO;
import com.projectirp.institutemanagementsystem.Models.TeacherProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AdminRoutes {

    public abstract ResponseEntity<String> dashboard();

//    public abstract ResponseEntity<?> finance();
    /**
     * Teachers
     * POST	/api/admin/az-teachers/register-teacher	Registers a new teacher.
     * GET	/api/admin/az-teachers/fetch-all-teachers	Retrieves a list of all teachers.
     * GET	/api/admin/az-teachers/fetch-teachers-by-class/:class	Fetches teachers assigned to a specific class.
     * GET	/api/admin/az-teachers/fetch-teachers-by-class-and-subject/:class/:subject	Searches for a teacher by class and subject.
     * PUT	/api/admin/az-teachers/update-teacher/:teacherRegId	Updates a teacher's details by their registration ID.
     * DELETE	/api/admin/az-teachers/delete-teacher/:teacherRegId	Deletes a teacher by their registration ID.
     *
     */

    public abstract ResponseEntity<String> registerTeacher(@RequestBody TeacherRegistrationDto dto);

    public abstract ResponseEntity<List<TeacherProfile>> fetchAllTeachers();
    public abstract ResponseEntity<TeacherResponseDTO> fetchTeachersByClass(@PathVariable int teacherClass);




}
