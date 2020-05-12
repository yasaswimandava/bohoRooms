package com.mindtree.bohorooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.bohorooms.entity.Checkout;
@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

}
