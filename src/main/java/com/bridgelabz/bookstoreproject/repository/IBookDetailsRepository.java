package com.bridgelabz.bookstoreproject.repository;

import com.bridgelabz.bookstoreproject.model.BookDetails;
import com.bridgelabz.bookstoreproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookDetailsRepository extends JpaRepository<BookDetails,Integer> {
    @Query(value = "SELECT * FROM book_details WHERE book_name Like 'H%' ",nativeQuery = true)
            //(value = "SELECT * FROM book_details inner join book_name on book_details.book_id=book_name.book_id ",nativeQuery=true)
    List<BookDetails> findBookByName(String bookName);


}
