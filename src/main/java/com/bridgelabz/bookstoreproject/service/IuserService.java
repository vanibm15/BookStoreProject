package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.dto.LoginDTO;
import com.bridgelabz.bookstoreproject.dto.ResponseDTO;
import com.bridgelabz.bookstoreproject.dto.UserDTO;
import com.bridgelabz.bookstoreproject.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IuserService {
    String getMessage();

    String addUser(UserDTO userdto);

    Optional<UserModel> getUserData(String token);

    Optional<UserModel> getUserById(int getId);

    List<UserModel> getListOfUsers();

    void deleteUser(int id);

    UserModel updateUser(int getId, UserDTO userDTO);

    Optional<UserModel>getUserByEmail(String department);

   String loginValidation(LoginDTO logindto);

    UserModel forgotPassword(String emailId, String newPassword);
}
