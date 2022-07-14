package com.bridgelabz.bookstoreproject.model;


import com.bridgelabz.bookstoreproject.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@ToString
public class OrderDetails {


    @Id
    @GeneratedValue
    private int orderId;
    private LocalDate date = LocalDate.now();
    private int price;
    private int quantity;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @ManyToOne
    @JoinColumn(name = "book_details_book_id")
    private BookDetails bookDetails;
    private Boolean cancel;

    public OrderDetails( UserModel userModel, BookDetails book,OrderDTO orderDTO) {
        this.user=userModel;
            this.bookDetails=book;
            this.quantity=orderDTO.getQuantity();
            this.address=orderDTO.getAddress();
            this.price=orderDTO.getPrice();
            this.cancel=orderDTO.isCancel();
        }


    }



