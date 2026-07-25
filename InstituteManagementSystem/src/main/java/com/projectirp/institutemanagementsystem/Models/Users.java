package com.projectirp.institutemanagementsystem.Models;

import com.projectirp.institutemanagementsystem.Utilities.InstituteRoles;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // DB handles ID (AUTO_INCREMENT), MySQL, Postgres
    private Long userId;

    private String fullName;
    private String contact;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private InstituteRoles roles;

    private boolean isActive;  /// col in DB (postgres) → is_active


}

