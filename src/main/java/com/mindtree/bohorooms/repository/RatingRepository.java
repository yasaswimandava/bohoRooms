package com.mindtree.bohorooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.bohorooms.entity.Rating;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
