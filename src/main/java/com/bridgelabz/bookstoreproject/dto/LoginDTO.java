package com.bridgelabz.bookstoreproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
public class LoginDTO {

    private  String email;
    private  String password;

}
