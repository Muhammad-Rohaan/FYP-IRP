package com.projectirp.institutemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teacher_classes")
public class TeacherClasses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_number", nullable = false)
    private Integer classNumber;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "teacher_profile_id", nullable = false)
//    private TeacherProfile teacherProfile;

}