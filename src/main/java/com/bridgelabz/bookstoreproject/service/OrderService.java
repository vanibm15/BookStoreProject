package com.bridgelabz.bookstoreproject.service;


import com.bridgelabz.bookstoreproject.dto.OrderDTO;
import com.bridgelabz.bookstoreproject.exception.UserException;
import com.bridgelabz.bookstoreproject.model.BookDetails;
import com.bridgelabz.bookstoreproject.model.CartDetails;
import com.bridgelabz.bookstoreproject.model.OrderDetails;
import com.bridgelabz.bookstoreproject.model.UserModel;
import com.bridgelabz.bookstoreproject.repository.IBookDetailsRepository;
import com.bridgelabz.bookstoreproject.repository.IOrderRepository;
import com.bridgelabz.bookstoreproject.repository.IcartRepository;
import com.bridgelabz.bookstoreproject.repository.IuserRepository;
import com.bridgelabz.bookstoreproject.util.EmailSenderService;
import com.bridgelabz.bookstoreproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    EmailSenderService sender;
    @Autowired
    IBookDetailsRepository bookRepo;

    @Autowired
    IOrderRepository orderRepo;

    @Autowired
    IuserRepository userRepo;


    @Override
    public String getMessage() {
        return "welcome to bookstore";
    }

    @Override
    public OrderDetails addToOrder(OrderDTO orderDTO) {
        Optional<UserModel> isUserPresent = userRepo.findById(orderDTO.getUserId());
        if (isUserPresent.isPresent()) {
            BookDetails book = bookRepo.findById(orderDTO.getBookId()).orElseThrow();
            OrderDetails orderDetail = new OrderDetails(isUserPresent.get(), book, orderDTO);
            sender.sendEmail(isUserPresent.get().getEmail(), "Test Mail", isUserPresent.get().getFirstName() +
                    "Your Order has been placed Successfully" + "\n" + book.toString());
            return orderRepo.save(orderDetail);

        } else throw new UserException("inValid User");
    }

    @Override
    public Optional<OrderDetails> getOrderById(int getOrderId) {
        Optional<OrderDetails> orderDetails = orderRepo.findById(getOrderId);
        if (orderDetails.isPresent()) {
            return orderDetails;
        } else {
            throw new UserException("Order Id not found");
        }
    }

    @Override
    public List<OrderDetails> getListOfOrderDetails() {
        List<OrderDetails> order = orderRepo.findAll();
        return order;
    }

    @Override
    public void deleteBookFromOrder(int orderId) {
        orderRepo.deleteById(orderId);
    }

    @Override
    public OrderDetails updateOrder(int getOrderId, OrderDTO orderDTO) {

        Optional<OrderDetails> order = orderRepo.findById(getOrderId);
        order.get().setQuantity(orderDTO.getQuantity());
        order.get().setAddress(orderDTO.getAddress());
        order.get().setPrice(orderDTO.getPrice());
        order.get().setCancel(orderDTO.isCancel());
        orderRepo.save(order.get());
        return order.get();
    }

    @Override
    public List<CartDetails> deleteAll() {
        orderRepo.deleteAll();
        return null;
    }

}







