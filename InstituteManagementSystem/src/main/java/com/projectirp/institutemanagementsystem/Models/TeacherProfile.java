package com.projectirp.institutemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@DynamicUpdate
public class TeacherProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @ToString.Exclude
    private Users userId;

    @Column(nullable = false)
    private String teacherFullName;

    @Column(nullable = false, unique = true, length = 50)
    private String teacherRegId;

    @Column(nullable = false, unique = true, length = 20)
    private String cnic;

    @Column(nullable = false)
    private String qualification;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private LocalDateTime joiningDate = LocalDateTime.now();

    //    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "teacher_profile_id", nullable = false) // This puts the FK on the child table
    private List<TeacherSubject> subjects = new ArrayList<>();

    //        @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "teacher_profile_id", nullable = false)
    private List<TeacherClasses> classes = new ArrayList<>();

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer age;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


//    public TeacherProfile(String teacherRegId, String cnic, String qualification, Double salary, LocalDateTime joiningDate, List<String> subjects, List<Integer> classes, String address, Integer age) {
//        this.teacherRegId = teacherRegId;
//        this.cnic = cnic;
//        this.qualification = qualification;
//        this.salary = salary;
//        this.joiningDate = joiningDate;
//        this.subjects = subjects;
//        this.classes = classes;
//        this.address = address;
//        this.age = age;
//    }

}