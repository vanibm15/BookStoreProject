package com.bridgelabz.bookstoreproject.controller;
import com.bridgelabz.bookstoreproject.dto.LoginDTO;
import com.bridgelabz.bookstoreproject.dto.ResponseDTO;
import com.bridgelabz.bookstoreproject.dto.UserDTO;
import com.bridgelabz.bookstoreproject.model.UserModel;
import com.bridgelabz.bookstoreproject.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IuserService service;

    @GetMapping("/home")
    public String home() {
        String message = service.getMessage();
        return message;
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<ResponseDTO> addUserData(@Valid @RequestBody UserDTO userdto) {
        String addUser = service.addUser(userdto);
        ResponseDTO dto = new ResponseDTO("Contact added successfully", addUser);
        return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/retrieve/{token}")
    public ResponseEntity<ResponseDTO> getUserData(@PathVariable String token) {
        Optional<UserModel> getUserId = service.getUserData(token);
        ResponseDTO dto = new ResponseDTO("Contact retrieved successfully ", getUserId);
        return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/getUser/{getId}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable int getId) {
        Optional<UserModel> userId = service.getUserById(getId);
        ResponseDTO response = new ResponseDTO("User retrieved successfully by id:" + " " + getId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/getUsers")
    public ResponseEntity<ResponseDTO> getUsers() {

        List<UserModel> users = service.getListOfUsers();
        ResponseDTO response = new ResponseDTO("Retrieved list of users all data Successfully", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/deleteUser")
    public ResponseEntity<ResponseDTO> deleteUser(@RequestParam int id) {
        service.deleteUser(id);
        ResponseDTO response = new ResponseDTO("User deleted successfully", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/updateUser/{getId}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable int getId, @Valid @RequestBody UserDTO userDTO) {
        UserModel updateUser = service.updateUser(getId, userDTO);
        ResponseDTO response = new ResponseDTO("User updated successfully", updateUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/UserEmail/{email}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable String email) {
        Optional<UserModel> users = service.getUserByEmail(email);
        ResponseDTO responseDTO = new ResponseDTO("Get call for Users Email", users);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO logindto) {
        String userLogin = service.loginValidation(logindto);
        ResponseDTO responseDTO = new ResponseDTO("login successfull", userLogin);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<ResponseDTO> forgotPassword(@RequestParam String emailId,
                                                      @RequestParam String newPassword) {
        UserModel userData = service.forgotPassword(emailId, newPassword);
        ResponseDTO responseDTO = new ResponseDTO("Password Updated Successfully", userData);
        return new ResponseEntity<> (responseDTO, HttpStatus.OK);
    }



}


