package com.ntvspace.security.usecure.Common.Data.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

/**
 * Represents a user role
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`Role`")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private Date created = new Date();
}
