package com.bridgelabz.bookstoreproject.model;

import com.bridgelabz.bookstoreproject.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_model")
@Data
@NoArgsConstructor
@ToString
public class UserModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="id")
    private int userId;
    private String firstName;
    private String LastName;
    private String email;
    private String state;
    private String password;

private LocalDate DOB;
    @ElementCollection
    @CollectionTable(name = "email", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "email")
    private List<String> userEmail;
public UserModel(UserDTO userdto){
    this.firstName=userdto.getFirstName();
    this.LastName=userdto.getLastName();
    this.email=userdto.getEmail();
    this.state=userdto.getState();
    this.password=userdto.getPassword();
    this.DOB=userdto.getDOB();
    this.userEmail=userdto.getUserEmail();

}









}
