package com.projectirp.institutemanagementsystem.Models;

import com.projectirp.institutemanagementsystem.Utilities.FeeMonths;
import com.projectirp.institutemanagementsystem.Utilities.FeeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "fees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "std_id", nullable = false)
    @ToString.Exclude
    private StudentProfile stdId;

    @Column(nullable = false, length = 50)
    private String rollNo;

    private String studentName;
    private String className;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeeMonths month;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Double feesAmount = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeeStatus status = FeeStatus.PENDING;

    private String collectedBy;

    private LocalDateTime collectedDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}