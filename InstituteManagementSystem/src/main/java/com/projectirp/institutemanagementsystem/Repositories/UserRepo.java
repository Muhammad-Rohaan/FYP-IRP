package com.projectirp.institutemanagementsystem.Repositories;

import com.projectirp.institutemanagementsystem.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    @Query(value = "select full_name from users where roles='ADMIN'", nativeQuery = true)
    List<String> findByAdminName();



}
