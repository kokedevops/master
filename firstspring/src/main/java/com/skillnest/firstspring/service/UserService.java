package com.skillnest.firstspring.service;

import com.skillnest.firstspring.model.User;
import com.skillnest.firstspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    //@Autowired
    private final UserRepository userRepository;
    
    /*public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User newUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User user) {
        Optional<User> userExist = userRepository.findById(id);
        if(userExist.isPresent()){
            user.setId(id);
            return userRepository.save(user);
        }

        return null;
    }

    public boolean deleteUser(Long id) {
        Optional<User> userExist = userRepository.findById(id);
        if(userExist.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
