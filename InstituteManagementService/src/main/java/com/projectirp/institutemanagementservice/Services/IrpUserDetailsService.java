package com.projectirp.IrpAuthenticationService.Services;

import com.projectirp.IrpAuthenticationService.Models.UserPrincipal;
import com.projectirp.IrpAuthenticationService.Models.Users;
import com.projectirp.IrpAuthenticationService.Repositories.UserRepo;
import com.projectirp.IrpAuthenticationService.Utilities.InstituteRoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class IrpUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//        Users user = userRepo.findByFullName(username);
        Users user = userRepo.findByEmail(userEmail);

        if (user == null) {
            System.out.println("User with Email: "+userEmail+" not found");
            throw new UsernameNotFoundException("User Not Found");
        }
//        System.out.println(user.getEmail());
//        System.out.println(user.getFullName());
        return new UserPrincipal(user);
    }


}
