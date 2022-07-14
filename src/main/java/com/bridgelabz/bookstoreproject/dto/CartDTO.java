package com.bridgelabz.bookstoreproject.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CartDTO {
    private int userId;
    private int bookId;
    private int quantity;

}
