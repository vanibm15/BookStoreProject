package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.dto.LoginDTO;
import com.bridgelabz.bookstoreproject.dto.UserDTO;
import com.bridgelabz.bookstoreproject.exception.UserException;
import com.bridgelabz.bookstoreproject.model.UserModel;
import com.bridgelabz.bookstoreproject.repository.IuserRepository;
import com.bridgelabz.bookstoreproject.util.EmailSenderService;
import com.bridgelabz.bookstoreproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IuserService {

    @Autowired
    EmailSenderService sender;
    @Autowired
    IuserRepository repo;
    @Autowired
    TokenUtil tokenUtil;

    @Override
    public String getMessage() {
        return "Welcome to BookStore Service";
    }

    @Override
    public String addUser(UserDTO userdto) {
        UserModel userModel = new UserModel(userdto);
        repo.save(userModel);
        String token = tokenUtil.createToken(userModel.getUserId());
        sender.sendEmail(userModel.getEmail(), "Test Email", "Registered SuccessFully1 :" + userModel.toString());
        return token;
    }

    @Override
    public Optional<UserModel> getUserData(String token) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserModel> userModel = repo.findById(id);
        if (userModel.isPresent()) {
            sender.sendEmail(userModel.get().getEmail(), "TestMail",
                    "Retrieved user successfully :click here->" + "http://localhost:8080/bookstore/retrieve/" + token);
            return userModel;
        } else {
            throw new UserException("user not found");
        }
    }

    @Override
    public Optional<UserModel> getUserById(int getId) {
        Optional<UserModel> userModel = repo.findById(getId);
        if (userModel.isPresent()) {
            return userModel;
        } else {
            throw new UserException("user not found");
        }
    }

    @Override
    public List<UserModel> getListOfUsers() {
        List<UserModel> users = repo.findAll();
        return users;
    }

    @Override
    public void deleteUser(int id) {
        Optional<UserModel> userModel = repo.findById(id);
        repo.deleteById(id);
        sender.sendEmail(userModel.get().getEmail(), "Test Mail-BookStore",
                "User deleted Successfully" + userModel.toString());
    }

    @Override
    public UserModel updateUser(int getId, UserDTO userDTO) {
        Optional<UserModel> newUser = repo.findById(getId);
        if (newUser.isPresent() && newUser.get().getEmail() == userDTO.getEmail()) {
            newUser.get().setFirstName(userDTO.getFirstName());
            newUser.get().setLastName(userDTO.getLastName());
            newUser.get().setState(userDTO.getState());
            newUser.get().setEmail(userDTO.getEmail());
            newUser.get().setDOB(userDTO.getDOB());
            newUser.get().setPassword(userDTO.getPassword());
            repo.save(newUser.get());
            sender.sendEmail(newUser.get().getEmail(), "Test Mail-BookStore",
                    "To get Updated User: click here->" +
                            "http://localhost:8080/bookstore/getUser/" + getId);
            return newUser.get();
        } else {
            return null;
        }
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        Optional<UserModel> userModel = repo.findUserByEmail(email);
        return userModel;
    }

    @Override
    public String loginValidation(LoginDTO logindto) {
        Optional<UserModel> userPresent = repo.findUserByEmail(logindto.getEmail());
        if (userPresent.isPresent()) {
            String password = userPresent.get().getPassword();
            if (password.equals(logindto.getPassword())) {
                String token = tokenUtil.createToken(userPresent.get().getUserId());
                return token;
            } else {
                throw new UserException(" Password is incorrect");
            }
        }
        throw new UserException("user is invalid");
    }

    @Override
    public UserModel forgotPassword(String emailId, String password) {
        Optional<UserModel> isUserPresent = repo.findUserByEmail(emailId);
        if (isUserPresent.isPresent()) {
            isUserPresent.get().setPassword(password);
            return repo.save(isUserPresent.get());

        } else {
            throw new UserException("Invalid Email");
        }
    }
}


