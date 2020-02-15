package com.ntvspace.security.usecure.Services;

import com.ntvspace.security.usecure.Common.Data.Entities.Role;
import com.ntvspace.security.usecure.Repositories.RoleRepository;
import com.ntvspace.security.usecure.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collection;

/**
 * Provides operations to manage user roles
 */

@Service
public class RoleService {

    @Autowired private UserRepository _userRepository;
    @Autowired private RoleRepository _roleRepository;

    // Retrieves list user roles
    public Collection<Role> GetRoles() {
        return _roleRepository.findAll();
    }

    // Retrieves a single user role by id
    public Role GetById(int roleId) throws RoleNotFoundException {
        return _roleRepository.findById(roleId)
                    .orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }


    // Link a user to a role
//    public Role LinkUserToRole(long userId, int roleId) throws RoleNotFoundException {
//
//        User user = _userRepository.findById(userId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        Role role = _roleRepository.findByUserId(roleId)
//                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
//
//        /* when user account type is changed
//            if(user.accountType == Enum.PREMIUM)
//        */
//        _userRepository.save()
//    }

}
