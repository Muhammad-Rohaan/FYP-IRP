package com.projectirp.institutemanagementsystem.Models;


import com.projectirp.institutemanagementsystem.Utilities.AcademicFields;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @ToString.Exclude
    private Users userId;

    @Column(nullable = false, unique = true, length = 50)
    private String rollNo;

    @Column(nullable = false)
    private String stdName;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false, length = 11)
    private String fatherPhone;

    private String contact;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String className;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcademicFields field;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}