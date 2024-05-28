package com.aula.poo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.poo.demo.exception.UserNotFoundException;
import com.aula.poo.demo.model.User;
import com.aula.poo.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void insertUser(User u){
        userRepository.save(u);
    }

    public User findUserById(Long id) throws UserNotFoundException{
        
        Optional<User> opUser = userRepository.findById(id);

        if (opUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        return opUser.get();

    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(User u){
        userRepository.delete(u);
    }

    public void deleteUserById(Long id) throws UserNotFoundException{
        
        Optional<User> opUser = userRepository.findById(id);

        if (opUser.isEmpty()){
            throw new UserNotFoundException("User " + id + "not found!");
        }

        User u = opUser.get();
        userRepository.delete(u);
        
    }

    public void updateNameById(Long id, String newName) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User "+id+" not found");
        }

        User u = userOptional.get();
        u.setName(newName);

        userRepository.save(u);
    }

    public User searchEmail(String email) throws UserNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("User " + email + " not found!");
        }

        return user;

    }

}
