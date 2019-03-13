package com.vironit.airticketsbooking.springapp.controller;

import com.vironit.airticketsbooking.springapp.entity.User;

import com.vironit.airticketsbooking.springapp.exception.UserAlreadyExistsException;
import com.vironit.airticketsbooking.springapp.exception.handling.ValidationErrorBuilder;
import com.vironit.airticketsbooking.springapp.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class UserController {
    private final static Logger LOGGER = LogManager.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/", produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users == null) {
            LOGGER.error("Users are not found.");
            throw new EntityNotFoundException("Users not found");
        }
        LOGGER.info("Get all users");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/user/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createUser(@Validated @RequestBody User user, BindingResult result) throws UserAlreadyExistsException {
        if (result.hasErrors()) {
            LOGGER.info("There are problems with validation of fields during registration a new user");
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(result));
        }
        if(userService.isUserWithSameLoginExist(user.getEmail())){
            LOGGER.error("Unable to create. User with login {} already exist", user.getEmail());
            throw new UserAlreadyExistsException("User with login " + user.getEmail() + " already exist");
        }
        if(userService.isUserWithSamePassportNumberExist(user.getPassport_number())){
            LOGGER.error("Unable to create. User with passport number {} already exist", user.getPassport_number());
            throw new UserAlreadyExistsException("User with passport number " + user.getPassport_number() + " already exist");
        }
        if(userService.isUserWithSamePhoneNumberExist(user.getPhone_number())){
            LOGGER.error("Unable to create. User with phone number {} already exist", user.getPhone_number());
            throw new UserAlreadyExistsException("User with phone number " + user.getPhone_number() + " already exist");
        }
        String password = user.getPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodingPassword = passwordEncoder.encode(password);
        user.setPassword(encodingPassword);
        userService.addUser(user);
        LOGGER.log(Level.INFO, user.getName() + " " + user.getSurname() + " is registered now");
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("id")long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            LOGGER.error("User with id {} not found.", id);
            throw new EntityNotFoundException("User not found");
        }
        LOGGER.info("Get user with id {}", id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/user/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateUser(@PathVariable("id")long id,
                                        @Validated @RequestBody User user,
                                        BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.info("There are problems with validation of fields during updating user profile");
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(result));
        }
        LOGGER.info("Updating User with id {}", id);
        User currentUser = userService.findUserById(id);
        if (currentUser == null) {
            LOGGER.error("Unable to update. User with id {} not found.", id);
            throw new EntityNotFoundException("User not found");
        }
        String password = user.getPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodingPassword = passwordEncoder.encode(password);
        currentUser.setPassword(encodingPassword);
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setDob(user.getDob());
        userService.updateUser(currentUser);
        LOGGER.log(Level.INFO, "Personal profile of " + currentUser.getName() + " " + currentUser.getSurname() + " updated ");
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @PutMapping(value = "/user/{id}/recharge_balance_by={moneyAmount}", produces = "application/json")
    public ResponseEntity<User> addMoney(@PathVariable("id")long id, @PathVariable("moneyAmount")double moneyAmount)
    {
        LOGGER.info("Recharge balance of user with id {}", id);
        User user = userService.findUserById(id);
        if (user == null) {
            LOGGER.error("Unable to delete. User with id {} not found.", id);
            throw new EntityNotFoundException("User not found");
        }
        userService.addMoney(user, moneyAmount);
        LOGGER.info("The balance of " + user.getEmail() + " recharged by " + moneyAmount + " BYN");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity<User> deleteUser(@PathVariable("id")long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            LOGGER.error("Unable to delete. User with id {} not found.", id);
            throw new EntityNotFoundException("User not found");
        }
        userService.deleteUser(user);
        LOGGER.info("Delete user with id {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
