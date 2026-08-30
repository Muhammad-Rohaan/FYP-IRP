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

    @Query(value = """
            select teacher_full_name from teacher_profiles as tp\s
            inner join teacher_classes as tc\s
            on tp.id = tc.teacher_profile_id
            inner join teacher_subjects as ts
            on ts.teacher_profile_id = tp.id
            where class_number = :teacherClass AND subject = :teacherSubject;
            """,
            nativeQuery = true)
    List<String> fetchTeachersByClassAndSubject(@Param("teacherClass") int teacherClass, @Param("teacherSubject") String teacherSubject);

    @Query(value = """
            select * from teacher_profiles where teacher_reg_id = :teacherRegId;
            """,
            nativeQuery = true)
    TeacherProfile findTeacherByRegId(@Param("teacherRegId") String teacherRegId);

    @Query(value = """
            DELETE * from teacher_profiles where teacher_reg_id = :teacherRegId;
            """,
            nativeQuery = true)
    String deleteTeacherByRegId(String teacherRegId);
}










