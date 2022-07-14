package com.bridgelabz.bookstoreproject.controller;

import com.bridgelabz.bookstoreproject.dto.CartDTO;
import com.bridgelabz.bookstoreproject.dto.ResponseDTO;
import com.bridgelabz.bookstoreproject.model.BookDetails;
import com.bridgelabz.bookstoreproject.model.CartDetails;
import com.bridgelabz.bookstoreproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService service;



    @GetMapping("/home")
    public String home() {
        String message = service.getMessage();
        return message;
    }


    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO) {
        CartDetails cart = service.addToCart(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO(" Added to cart Successfully", cart);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);

    }


    @GetMapping("/cartById/{getCartId}")
    public ResponseEntity<ResponseDTO> getCartDetails(@PathVariable int getCartId) {
        Optional<CartDetails> cart= service.getCartById(getCartId);
        ResponseDTO response = new ResponseDTO("Cart details retrieved successfully by id:" + " " + getCartId, cart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/cartDetails")
    public ResponseEntity<ResponseDTO> getAll() {
        List<CartDetails> cartDetailsList = service.getListOfCartDetails();
        ResponseDTO response = new ResponseDTO(" Retrieved cart Detailed list ", cartDetailsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/deleteById/{cartId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int cartId) {
        service.deleteBook(cartId);
        ResponseDTO response = new ResponseDTO("Book removed from cart", cartId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseDTO> deleteAll()
    {
        List<CartDetails> removeAll = service.deleteAll();
        ResponseDTO response = new ResponseDTO("Cart empty", removeAll);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    @PutMapping("/updateCart/{CartId}")
    public ResponseEntity<ResponseDTO> updateCart(@PathVariable int CartId,
                                                  @RequestBody CartDTO cartDTO) {
        CartDetails updateCart = service.updateCart(CartId, cartDTO);
        ResponseDTO response = new ResponseDTO("Cart updated successfully", updateCart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
