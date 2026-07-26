package com.projectirp.institutemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "result_images", indexes = {
        @Index(name = "idx_class_test", columnList = "class_name, test_name", unique = true),
        @Index(name = "idx_class_name", columnList = "class_name"),
        @Index(name = "idx_uploaded_by", columnList = "uploaded_by")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "test_name", nullable = false)
    private String testName;

    @Column(nullable = false)
    private String imageUrl;

    private String publicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by", nullable = false)
    @ToString.Exclude
    private Users uploadedBy;

    @Column(nullable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}