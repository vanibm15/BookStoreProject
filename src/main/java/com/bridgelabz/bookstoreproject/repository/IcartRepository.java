package com.bridgelabz.bookstoreproject.repository;

import com.bridgelabz.bookstoreproject.model.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IcartRepository extends JpaRepository<CartDetails,Integer> {
}
