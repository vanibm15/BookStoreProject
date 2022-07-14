package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.dto.OrderDTO;
import com.bridgelabz.bookstoreproject.model.CartDetails;
import com.bridgelabz.bookstoreproject.model.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    String getMessage();

    OrderDetails addToOrder( OrderDTO orderDTO);

    Optional<OrderDetails> getOrderById(int getOrderId);

    List<OrderDetails> getListOfOrderDetails();

    void deleteBookFromOrder(int orderId);

    OrderDetails updateOrder(int getId, OrderDTO orderDTO);

    List<CartDetails> deleteAll();
}
