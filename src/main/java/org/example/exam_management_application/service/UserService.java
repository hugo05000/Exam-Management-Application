package org.example.exam_management_application.service;

import org.example.exam_management_application.model.User;
import org.example.exam_management_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des utilisateurs dans l'application.
 *
 * <p>Ce service offre les opérations CRUD sur l'entité User en interagissant avec le repository associé.
 * Il permet de récupérer, créer, modifier et supprimer des utilisateurs (étudiants et enseignants),
 * facilitant ainsi leur gestion au sein du système.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see User
 * @see UserRepository
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * Récupère la liste de tous les utilisateurs enregistrés.
     *
     * @return Liste de tous les utilisateurs existants.
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Récupère un utilisateur spécifique par son identifiant.
     *
     * @param id Identifiant unique de l'utilisateur.
     * @return Utilisateur s'il existe, sinon un Optional vide.
     */
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    /**
     * Crée un nouvel utilisateur dans la base de données.
     *
     * @param user L'utilisateur à ajouter.
     * @return L'utilisateur nouvellement créé.
     */
    public User createUser(User user){
        return userRepository.save(user);
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param id Identifiant de l'utilisateur à modifier.
     * @param user Données mises à jour de l'utilisateur.
     * @return Utilisateur mis à jour s'il existe, sinon un Optional vide.
     */
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
            updatedUser.setActive(user.isActive());

            User userUpdated = userRepository.save(updatedUser);
            return Optional.of(userUpdated);
        }
        return Optional.empty();
    }

    /**
     * Supprime un utilisateur en fonction de son identifiant.
     *
     * @param id Identifiant de l'utilisateur à supprimer.
     * @return true si l'utilisateur a été supprimé avec succès, false s'il n'existe pas.
     */
    public boolean deleteUser(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
