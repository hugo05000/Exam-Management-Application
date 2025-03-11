package org.example.exam_management_application.controller;

import org.example.exam_management_application.model.User;
import org.example.exam_management_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST assurant la gestion des utilisateurs (étudiants et enseignants) au sein de l'application.
 *
 * <p>Ce contrôleur permet de réaliser toutes les opérations CRUD nécessaires à la gestion des utilisateurs.
 * Les fonctionnalités offertes incluent la création, la consultation, la mise à jour et la suppression
 * des profils utilisateurs, facilitant ainsi la gestion administrative des données relatives aux étudiants
 * et aux enseignants.</p>
 *
 * @author Hugo MARCEAU
 * @version 1.0
 * @see User
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Récupère la liste complète de tous les utilisateurs inscrits.
     *
     * @return ResponseEntity avec une liste d'utilisateurs et le statut HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Ajoute un nouvel utilisateur (étudiant ou enseignant) dans la base de données.
     *
     * @param user Utilisateur à créer.
     * @return ResponseEntity avec l'utilisateur créé et le statut HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    /**
     * Récupère un utilisateur spécifique par son identifiant.
     *
     * @param id Identifiant unique de l'utilisateur.
     * @return ResponseEntity avec l'utilisateur trouvé et le statut HTTP OK, sinon NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param id Identifiant de l'utilisateur à mettre à jour.
     * @param user Données mises à jour pour l'utilisateur.
     * @return ResponseEntity avec l'utilisateur mis à jour et le statut HTTP OK, sinon NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> updatedUser = userService.updateUser(id, user);
        if (updatedUser.isPresent()) {
            return new ResponseEntity<>(updatedUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Supprime un utilisateur spécifié par son identifiant.
     *
     * @param id Identifiant de l'utilisateur à supprimer.
     * @return ResponseEntity avec statut HTTP OK si supprimé, sinon NOT_FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
