package com.projectirp.institutemanagementsystem.Repositories;

import com.projectirp.institutemanagementsystem.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
    Users findByFullName(String username);
}
