package com.rbondarovich.dao.repository;

import com.rbondarovich.dao.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
