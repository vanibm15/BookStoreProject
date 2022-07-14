package com.bridgelabz.bookstoreproject.controller;

import com.bridgelabz.bookstoreproject.dto.BookDetailsDTO;
import com.bridgelabz.bookstoreproject.dto.ResponseDTO;
import com.bridgelabz.bookstoreproject.dto.UserDTO;
import com.bridgelabz.bookstoreproject.model.BookDetails;
import com.bridgelabz.bookstoreproject.model.UserModel;
import com.bridgelabz.bookstoreproject.service.IBookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookDetailsController {

    @Autowired
    IBookDetailsService service;






    @GetMapping("/home")
    public String home() {
        String message = service.getMessage();
        return message;
    }



    @PostMapping("addBook")
    public ResponseEntity<ResponseDTO> addBookData( @RequestBody BookDetailsDTO bookDetailsdto) {
        BookDetails addBookDetails = service.addBookDetails(bookDetailsdto);
        ResponseDTO response = new ResponseDTO("Book added successfully", addBookDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/getBook/{getId}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable int getbookId) {
        Optional<BookDetails> book = service.getBookById(getbookId);
        ResponseDTO response = new ResponseDTO("Book retrieved successfully by id:" + " " + getbookId, book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/getBooks")
    public ResponseEntity<ResponseDTO> getBooks() {
        List<BookDetails> books = service.getListOfBooks();
        ResponseDTO response = new ResponseDTO("Retrieved list of Books Successfully", books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/deleteBook")
    public ResponseEntity<ResponseDTO> deleteBook(@RequestParam int bookId) {
        service.deleteBook(bookId);
        ResponseDTO response = new ResponseDTO("Book deleted successfully", bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    @PutMapping("/updateBookDetails/{getId}")
    public ResponseEntity<ResponseDTO> updateBookById(@PathVariable int getbookId,
                                                      @Valid @RequestBody BookDetailsDTO bookDetailsDTO) {
        BookDetails updateBook = service.updateBookById(getbookId, bookDetailsDTO);
        ResponseDTO response = new ResponseDTO("Book updated successfully", updateBook);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateBookQuantity/{getId}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable int getbookId,
                                                          @Valid @RequestParam int quantity) {
        BookDetails updateBookQuantity = service.updateBookQuantity(getbookId, quantity);
        ResponseDTO response = new ResponseDTO("Book Quantity updated successfully", updateBookQuantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/sortingDsc")
    public ResponseEntity<ResponseDTO> sortingBooksInDscOrder() {
        List<BookDetails> books = service.getListOfBooksInSortedDsc();
        ResponseDTO response = new ResponseDTO("Retrieved list of Books in Descending order", books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/sortingAce")
    public ResponseEntity<ResponseDTO> sortingBooksInAceOrder() {
        List<BookDetails> books = service.getListOfBooksInSortedAce();
        ResponseDTO response = new ResponseDTO("Retrieved list of Books in Ascending", books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/SearchbookName/{bookName}")
    public ResponseEntity<ResponseDTO> getbookByName(@PathVariable String bookName){
        List<BookDetails> getBookByName=service.getBookByName(bookName);
        ResponseDTO responseDTO=new ResponseDTO("Retrieved books successfully...! ",getBookByName);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }


}



















