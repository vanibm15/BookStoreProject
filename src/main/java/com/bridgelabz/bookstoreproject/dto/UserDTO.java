package com.bridgelabz.bookstoreproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@ToString
public class UserDTO {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Invalid  firstName")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Invalid  lastName")
    private String LastName;
    @NotNull(message = "email should not be null")
    private String email;
    @NotNull(message = "state should not be null")
    private String state;
    @NotNull(message = "password should not be null")
    private String password;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate DOB;
    private List<String> userEmail;

}
