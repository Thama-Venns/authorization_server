package com.ntvspace.security.usecure.Services;

import com.ntvspace.security.usecure.Common.Data.Entities.Role;
import com.ntvspace.security.usecure.Common.Data.Entities.User;
import com.ntvspace.security.usecure.Common.Util.Validator;
import com.ntvspace.security.usecure.Models.RegisterModel;
import com.ntvspace.security.usecure.Repositories.UserRepository;
import com.ntvspace.security.usecure.Repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired private UserRepository _userRepository;
    @Autowired private RoleRepository _roleRepository;
    @Autowired private ModelMapper _mapper;
    @Autowired private BCryptPasswordEncoder _passwordEncoder;
    private Validator _validator = new Validator();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = _userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    public User UserCreateUser(RegisterModel model) throws Exception {

        String email = model.getEmail();

        if (_userRepository.findByEmail(model.getEmail()).isPresent() || _userRepository.findByUsername(model.getUsername()).isPresent()) {
            throw new Exception("");
        }

        // Validate email
        if(!_validator.EmailIsValid(email))
            throw new Exception("Failed");

        // Validate password
        if(model.getPassword().contains(model.getUsername()) || !_validator.PasswordIsValid(model.getPassword()))
            throw new Exception("Failed");

        // Validate ID No
        if(!_validator.IdentificationNoIsValid(model.getIdentityNumber()))
            throw new Exception("Failed");

        Role role = new Role();
        role.setNAME("USER");
        role.setDESCRIPTION("System User");
        role.setCREATED(new Date());

        _roleRepository.save(role);

        User newUser = _mapper.map(model, User.class);

        String password_hash = _passwordEncoder.encode(model.getPassword());
        newUser.setPassword(password_hash);

        newUser.getRoles().add(role);
//        newUser.setRole(role);

        _userRepository.save(newUser);

        return newUser;
    }

    public void DeleteUser(long uid) {
        User user = _userRepository.findById(uid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        _userRepository.delete(user);
    }

}
