package com.projectirp.institutemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false)
    private String publicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by", nullable = false)
    @ToString.Exclude
    private Users uploadedBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}