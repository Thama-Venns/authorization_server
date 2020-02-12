package com.ntvspace.security.usecure.Models;

import com.ntvspace.security.usecure.Common.Data.Entities.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String identityNumber;
    private String telephoneNumber;
    private Date created;
    private Set<Role> roles;
}
