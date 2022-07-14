package com.bridgelabz.bookstoreproject.repository;

import com.bridgelabz.bookstoreproject.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails,Integer> {

}
