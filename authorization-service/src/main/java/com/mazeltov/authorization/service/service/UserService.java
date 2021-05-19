package com.mazeltov.authorization.service.service;

import com.mazeltov.authorization.service.dao.model.User;
import com.mazeltov.authorization.service.dao.repository.*;
import com.mazeltov.common.dto.*;
import com.mazeltov.common.exception.*;
import com.mazeltov.common.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    public void creteUser(UserDto userDto) throws UserAlReadyExistException {

        if (userRepository.findByUsername(userDto.getUserName()).isPresent()) {
            throw new UserAlReadyExistException(String.format("User %s already exist", userDto.getUserName()));
        } else {
            userRepository.save(new User(
                    userDto.getUserName(),
                    userDto.getName(),
                    userDto.getEmail(),
                    bCryptPasswordEncoder.encode(userDto.getPassword()),
                    true,
                    true,
                    true,
                    true,
                    UserRole.USER)
            );
        }

    }

    public void editUser(UserDto userDto) throws PasswordsAreNotTheSameExistException, UsernameNotFoundException {

        User user = userRepository
                .findByUsername(userDto.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", userDto.getUserName())));

        if (!user.getPassword().equals(bCryptPasswordEncoder.encode(userDto.getPassword())))
            throw new PasswordsAreNotTheSameExistException("Passwords are different");

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }

    public void editUserAsAdmin(UserForAdminDto userDto) throws PasswordsAreNotTheSameExistException, UsernameNotFoundException {

        User user = userRepository
                .findByUsername(userDto.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", userDto.getUserName())));

        if (!user.getPassword().equals(bCryptPasswordEncoder.encode(userDto.getPassword())))
            throw new PasswordsAreNotTheSameExistException("Passwords are different");

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        userRepository.save(user);
    }
}

