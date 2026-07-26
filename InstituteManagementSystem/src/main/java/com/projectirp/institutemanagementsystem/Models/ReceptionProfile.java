package com.projectirp.institutemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reception_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceptionProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @ToString.Exclude
    private Users userId;

    @Column(nullable = false)
    private String receptionistFullName;

    @Column(nullable = false, unique = true, length = 50)
    private String receptionRegId;

    @Column(nullable = false, unique = true, length = 20)
    private String cnic;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private LocalDateTime joiningDate = LocalDateTime.now();

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String address;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}