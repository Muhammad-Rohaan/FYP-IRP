package com.projectirp.institutemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teacher_subjects")
public class TeacherSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "teacher_profile_id", nullable = false)
//    private TeacherProfile teacherProfile;

}