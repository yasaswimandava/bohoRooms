package com.mindtree.bohorooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.bohorooms.entity.Room;
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{

}
