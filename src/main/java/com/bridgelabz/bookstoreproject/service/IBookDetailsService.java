package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.dto.BookDetailsDTO;
import com.bridgelabz.bookstoreproject.model.BookDetails;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface IBookDetailsService {


    String getMessage();

    BookDetails addBookDetails(@Valid BookDetailsDTO bookDetails);


    Optional<BookDetails> getBookData(String token);

    Optional<BookDetails> getBookById(int getbookId);

    List<BookDetails> getListOfBooks();

    void deleteBook(int bookid);

    BookDetails updateBookById(int getbookId, BookDetailsDTO bookDetailsDTO);


    BookDetails updateBookQuantity(int getbookId, int quantity);


    List<BookDetails> getListOfBooksInSortedDsc();

    List<BookDetails> getListOfBooksInSortedAce();

    List<BookDetails> getBookByName(String bookName);
}
