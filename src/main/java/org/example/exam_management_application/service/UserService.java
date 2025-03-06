package org.example.exam_management_application.service;

import org.example.exam_management_application.model.User;
import org.example.exam_management_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    // Récupère tous les utilisateurs
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Récupère un cours par son id
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    // Crée un nouvel utilisateur
    public User createUser(User user){
        return userRepository.save(user);
    }

    // Met à jour un utilisateur existant
    public Optional<User> updateUser(Long id, User user){
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()){
            User updatedUser = userOptional.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRole(user.getRole());
            updatedUser.setUsername(user.getUsername());

            User userUpdated = userRepository.save(updatedUser);
            return Optional.of(userUpdated);
        }
        return Optional.empty();
    }

    // Supprime un utilisateur par son identifiant
    public boolean deleteUser(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
