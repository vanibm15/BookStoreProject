package com.bridgelabz.bookstoreproject.controller;


import com.bridgelabz.bookstoreproject.dto.OrderDTO;
import com.bridgelabz.bookstoreproject.dto.ResponseDTO;
import com.bridgelabz.bookstoreproject.model.CartDetails;
import com.bridgelabz.bookstoreproject.model.OrderDetails;
import com.bridgelabz.bookstoreproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping("/home")
    public String home(){
        String message=service.getMessage();
        return message;
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody OrderDTO orderDTO) {
        OrderDetails cart = service.addToOrder(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Order placed successfully..!", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getOrderDetails/{getOrderId}")
    public ResponseEntity<ResponseDTO> getOrderDetails(@PathVariable int getOrderId) {
        Optional<OrderDetails> order = service.getOrderById(getOrderId);
        ResponseDTO response = new ResponseDTO("Order details retrieved successfully for id:" + " " + getOrderId,order);
        return new ResponseEntity<>(response, HttpStatus.OK);


    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll() {
        List<OrderDetails> order = service.getListOfOrderDetails();
        ResponseDTO response = new ResponseDTO("Retrieved List of Order Details Successfully",order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{orderId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int orderId) {
        service.deleteBookFromOrder(orderId);
        ResponseDTO response = new ResponseDTO("Book removed from order by Id",orderId );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/removeAll")
    public ResponseEntity<ResponseDTO> deleteAllOrder()
    {
        List<CartDetails> removeAll = service.deleteAll();
        ResponseDTO response = new ResponseDTO("Cart empty", removeAll);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/updateOrder/{getId}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable int getId,
                                                  @Valid @RequestBody OrderDTO orderDTO) {
        OrderDetails newOrder = service.updateOrder(getId, orderDTO);
        ResponseDTO response = new ResponseDTO("User updated successfully",newOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
