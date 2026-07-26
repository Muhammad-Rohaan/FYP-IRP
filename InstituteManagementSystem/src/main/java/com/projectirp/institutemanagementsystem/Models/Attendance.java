package com.projectirp.institutemanagementsystem.Models;

import com.projectirp.institutemanagementsystem.Utilities.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendances", indexes = {
        @Index(name = "idx_student_date", columnList = "student_id, date", unique = true)
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    @ToString.Exclude
    private StudentProfile studentId;

    @Column(nullable = false, length = 50)
    private String rollNo;

    private String studentName;
    private String className;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status = AttendanceStatus.PRESENT;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}