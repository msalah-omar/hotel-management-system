package com.example.test.service;

import com.example.test.entity.User;
import com.example.test.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public Page<User> getAll(Integer page, Integer size)
    {
       return userRepository.findAll(PageRequest.of(page,size));
    }
    public User getReferenceById(Integer id) {
        return userRepository.getById(id);
    }


    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }


    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Optional<User> getByUserName(String username)
    {

        return userRepository.findUserByUsername(username);
    }
    public Optional<User> getSearchUser(String username)
    {
        return userRepository.findUserByUsername(username);
    }

    public void changePassword(User user ,String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    public Boolean oldPasswordIsValid(User user ,String oldPassword)
    {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }


}
