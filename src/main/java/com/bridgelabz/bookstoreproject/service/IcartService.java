package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.dto.CartDTO;
import com.bridgelabz.bookstoreproject.model.BookDetails;
import com.bridgelabz.bookstoreproject.model.CartDetails;

import java.util.List;
import java.util.Optional;

public interface IcartService {
    String getMessage();


    CartDetails addToCart( CartDTO cartDTO);

    Optional<CartDetails> getCartById(int getCartId);

    List<CartDetails> getListOfCartDetails();

    void deleteBook(int cartId);

    List<CartDetails> deleteAll();


    CartDetails updateCart(int CartId, CartDTO cartDTO);
}