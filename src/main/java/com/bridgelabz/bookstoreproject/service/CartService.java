package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.dto.CartDTO;
import com.bridgelabz.bookstoreproject.exception.UserException;
import com.bridgelabz.bookstoreproject.model.BookDetails;
import com.bridgelabz.bookstoreproject.model.CartDetails;
import com.bridgelabz.bookstoreproject.model.UserModel;
import com.bridgelabz.bookstoreproject.repository.IBookDetailsRepository;
import com.bridgelabz.bookstoreproject.repository.IcartRepository;
import com.bridgelabz.bookstoreproject.repository.IuserRepository;
import com.bridgelabz.bookstoreproject.util.EmailSenderService;
import com.bridgelabz.bookstoreproject.util.TokenUtil;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements IcartService {
    @Autowired
    EmailSenderService sender;
    @Autowired
    IcartRepository cartRepo;
    @Autowired
    IBookDetailsRepository bookRepo;

    @Autowired
    IuserRepository userRepo;


    @Override
    public String getMessage() {

        return "welcome to book store";
    }

    @Override
    public CartDetails addToCart(CartDTO cartDTO) {

        Optional<UserModel> isUserPresent = userRepo.findById(cartDTO.getUserId());
        if (isUserPresent.isPresent()) {
            BookDetails book = bookRepo.findById(cartDTO.getBookId()).orElseThrow();
            CartDetails cart = new CartDetails(isUserPresent.get(), book, cartDTO.getQuantity());
            return cartRepo.save(cart);

        } else throw new UserException("Invalid User ");
    }


    @Override
    public Optional<CartDetails> getCartById(int getCartId) {
        Optional<CartDetails> cartDetails = cartRepo.findById(getCartId);
        return cartDetails;
    }

    @Override
    public List<CartDetails> getListOfCartDetails() {
        List<CartDetails> cart = cartRepo.findAll();
        return cart;
    }

    @Override
    public void deleteBook(int cartId) {
        cartRepo.deleteById(cartId);

    }

    @Override
    public List<CartDetails> deleteAll() {
        cartRepo.deleteAll();
        return null;

    }

    @Override
    public CartDetails updateCart(int CartId, CartDTO cartDTO) {
        Optional<CartDetails> cartDetails = cartRepo.findById(CartId);
        cartDetails.get().setQuantity(cartDTO.getQuantity());
        cartRepo.save(cartDetails.get());
        return cartDetails.get();

    }
}








