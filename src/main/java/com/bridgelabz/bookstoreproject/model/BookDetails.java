package com.bridgelabz.bookstoreproject.model;


import com.bridgelabz.bookstoreproject.dto.BookDetailsDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book_details")
@Data
@NoArgsConstructor
@ToString


public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="bookId")
    private int bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private int price;
    private int quantity;

    @ElementCollection
    @CollectionTable(name = "bookName", joinColumns = @JoinColumn(name = "bookId"))
    @Column(name = "bookName")
    private List<String>bookNames;
    public BookDetails(BookDetailsDTO bookDetailsdto) {
        this.authorName=bookDetailsdto.getAuthorName();
        this.bookDescription=bookDetailsdto.getBookDescription();
        this.bookImg=bookDetailsdto.getBookImg();
        this.bookName=bookDetailsdto.getBookName();
        this.price=bookDetailsdto.getPrice();
        this.quantity=bookDetailsdto.getQuantity();
        this.bookNames=bookDetailsdto.getBookNames();
    }
}
