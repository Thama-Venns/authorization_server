package com.ntvspace.security.usecure.Common.Data.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user role
 */

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String NAME;
    private String DESCRIPTION;
    private Date CREATED;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<>();
}
