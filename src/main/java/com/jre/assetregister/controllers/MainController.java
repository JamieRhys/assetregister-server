package com.jre.assetregister.controllers;

import com.jre.assetregister.database.entities.Asset;
import com.jre.assetregister.database.entities.Contact;
import com.jre.assetregister.database.entities.LoginUser;
import com.jre.assetregister.database.repositories.AssetRepository;
import com.jre.assetregister.database.repositories.ContactRepository;
import com.jre.assetregister.database.repositories.UserRepository;
import com.jre.assetregister.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class MainController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    AssetRepository assetRepository;

    @GetMapping("/assets")
    Iterable<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @GetMapping("/contacts")
    Iterable<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/users")
    Iterable<LoginUser> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    LoginUser newUser(@RequestBody LoginUser newUser) {
        return userRepository.save(newUser);
    }

    // Single User
    @GetMapping("/users/{id}")
    LoginUser getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    LoginUser replaceUser(@RequestBody LoginUser newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setUserRole(newUser.getUserRole());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setUserId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
