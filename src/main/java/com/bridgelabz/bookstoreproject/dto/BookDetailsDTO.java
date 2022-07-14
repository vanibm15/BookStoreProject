package com.bridgelabz.bookstoreproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class BookDetailsDTO {

   // @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Invalid  BookName")
    private String bookName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Invalid  BookName")
    private String authorName;
    @NotNull(message = "Book Description should not be null")
    private String bookDescription;
    @NotNull(message = "Book bookImg should not be null")
    private String bookImg;
    @NotNull(message = "Book price should not be null")
    private int price;
    @NotNull(message = "Book quantity should not be null")
    private int quantity;
  //  @NotNull(message = "Book Names should not be null")
    private List<String> bookNames;
}
