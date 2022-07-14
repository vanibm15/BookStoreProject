package com.bridgelabz.bookstoreproject.repository;

import com.bridgelabz.bookstoreproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IuserRepository extends JpaRepository<UserModel,Integer> {

  // @Query(value = "SELECT * FROM user_model inner join email on user_model.id=email.id" ,nativeQuery=true)
    Optional<UserModel> findUserByEmail(String email);





}
