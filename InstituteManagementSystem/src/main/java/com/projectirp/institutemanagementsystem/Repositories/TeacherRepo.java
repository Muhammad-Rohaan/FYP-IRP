package com.projectirp.institutemanagementsystem.Repositories;

import com.projectirp.institutemanagementsystem.Models.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<TeacherProfile, Long> {

    @Query(value = "select teacher_full_name from teacher_profiles as tp full join teacher_classes as tc on tp.id = tc.teacher_profile_id where class_number = :teacherClass;",
            nativeQuery = true)
    List<String> fetchTeachersByClass(@Param("teacherClass") int teacherClass);
}
