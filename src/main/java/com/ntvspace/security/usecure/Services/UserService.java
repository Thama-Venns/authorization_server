package com.ntvspace.security.usecure.Services;

import com.ntvspace.security.usecure.Common.Data.Entities.Role;
import com.ntvspace.security.usecure.Common.Data.Entities.User;
import com.ntvspace.security.usecure.Common.Util.Validator;
import com.ntvspace.security.usecure.Models.RegisterModel;
import com.ntvspace.security.usecure.Models.UserDto;
import com.ntvspace.security.usecure.Repositories.UserRepository;
import com.ntvspace.security.usecure.Repositories.RoleRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired private UserRepository _userRepository;
    @Autowired private RoleRepository _roleRepository;
    @Autowired private ModelMapper _mapper;
    @Autowired private BCryptPasswordEncoder _passwordEncoder;
    private Validator _validator = new Validator();

    /**Load user for authentication
     * @param uniqueName : Can be the user email or username
     * @return user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String uniqueName) throws UsernameNotFoundException {
        User user = _userRepository.findByEmail(uniqueName).orElseGet(() ->
                    _userRepository.findByUsername(uniqueName).orElseThrow(() -> new UsernameNotFoundException("User not found"))
        );
        return user;
    }

    //Retrieves a list of users
    public Collection<UserDto> GetAll() {
        Collection<User> users = _userRepository.findAll();
        Type typeCon = new TypeToken<Collection<UserDto>>() {}.getType();
        Collection<UserDto> usersList = _mapper.map(users, typeCon);
        return usersList;
    }

    // Retrieves a single user by ID
    public UserDto getById(long userId) {
        User usr = _userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return _mapper.map(usr, UserDto.class);
    }

    // Create/register a new user
    public User UserCreateUser(@NotNull RegisterModel model) throws Exception {

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

        Role role = new Role(0, "USER", "System User", new Date());
        Role role2 = new Role(0, "ADMIN", "System Admin", new Date());

        List<Role> roles = Arrays.asList(role, role2);

        User newUser = _mapper.map(model, User.class);

        String password_hash = _passwordEncoder.encode(model.getPassword());
        newUser.setPassword(password_hash);

        newUser.getRoles().addAll(roles);

        _userRepository.save(newUser);

        return newUser;
    }

    // Deletes a user
    public void DeleteUser(long uid) {
        User user = _userRepository.findById(uid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        _userRepository.delete(user);
    }

}
