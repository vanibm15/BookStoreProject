package com.bridgelabz.bookstoreproject.service;


import com.bridgelabz.bookstoreproject.dto.BookDetailsDTO;
import com.bridgelabz.bookstoreproject.exception.UserException;
import com.bridgelabz.bookstoreproject.model.BookDetails;
import com.bridgelabz.bookstoreproject.model.UserModel;
import com.bridgelabz.bookstoreproject.repository.IBookDetailsRepository;
import com.bridgelabz.bookstoreproject.util.EmailSenderService;
import com.bridgelabz.bookstoreproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class BookDetailsService implements IBookDetailsService {


    @Autowired
    EmailSenderService sender;
    @Autowired
    IBookDetailsRepository repo;
    @Autowired
    TokenUtil tokenUtil;

    @Override
    public String getMessage() {
        return "Welcome to Book store";
    }


    @Override
    public BookDetails addBookDetails(BookDetailsDTO bookDetailsDTO) {
        BookDetails bookDetails = new BookDetails(bookDetailsDTO);
        repo.save(bookDetails);
        sender.sendEmail("vanibm15@gmail.com",
                "Test Mail", "Book Added Successfully" + bookDetails.toString());
        return bookDetails;
    }

    @Override
    public Optional<BookDetails> getBookData(String token) {
        int id = tokenUtil.decodeToken(token);
        Optional<BookDetails> book = repo.findById(id);
        if (book.isPresent()) {
            return book;
        } else {
            throw new UserException("Book is out of stock");
        }
    }

    @Override
    public Optional<BookDetails> getBookById(int getbookId) {
        Optional<BookDetails> book = repo.findById(getbookId);
        if (book.isPresent()) {
            return book;
        } else {
            throw new UserException("Book not found");
        }
    }

    @Override
    public List<BookDetails> getListOfBooks() {
        List<BookDetails> books = repo.findAll();
        return books;
    }

    @Override
    public void deleteBook(int id) {
        Optional<BookDetails> bookDetails = repo.findById(id);
        repo.deleteById(id);
        sender.sendEmail("vanibm15@gmail.com", "Test Mail-BookStore",
                "Book deleted Successfully" + bookDetails.toString());
    }

    @Override
    public BookDetails updateBookById(int getId, BookDetailsDTO bookDetailsDTO) {
        Optional<BookDetails> newBook = repo.findById(getId);
        if (newBook.isPresent()) {
            newBook.get().setBookName(bookDetailsDTO.getBookName());
            newBook.get().setBookDescription(bookDetailsDTO.getBookDescription());
            newBook.get().setBookImg(bookDetailsDTO.getBookImg());
            newBook.get().setAuthorName(bookDetailsDTO.getAuthorName());
            newBook.get().setPrice(bookDetailsDTO.getPrice());
            repo.save(newBook.get());
            sender.sendEmail("vanibm15@gmail.com", "Test Mail-BookStore",
                    "To get Updated Book: click here->" +
                            "http://localhost:8080/book/getBook/" + getId);

            return newBook.get();
        } else {
            throw new UserException("Book not found");
        }
    }

    @Override
    public BookDetails updateBookQuantity(int getId, int quantity) {
        Optional<BookDetails> newBook = repo.findById(getId);
        if (newBook.isPresent()) {
            newBook.get().setQuantity(quantity);
            repo.save(newBook.get());
            return newBook.get();
        } else {
            throw new UserException("Book not found");
        }
    }

    @Override
    public List<BookDetails> getListOfBooksInSortedDsc() {
        List<BookDetails> bookDetails = repo.findAll(Sort.by("bookName").descending());
        return bookDetails;
    }

    @Override
    public List<BookDetails> getListOfBooksInSortedAce() {
        List<BookDetails> bookDetails = repo.findAll(Sort.by("bookName").ascending());
        return bookDetails;
    }

    @Override
    public List<BookDetails> getBookByName(String bookName) {
        return repo.findBookByName(bookName);
    }


}





