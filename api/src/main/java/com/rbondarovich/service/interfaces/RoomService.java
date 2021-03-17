package com.rbondarovich.service.interfaces;

import com.rbondarovich.service.bean.RoomBean;

public interface RoomService {

    Iterable<RoomBean> getAllRooms();

    RoomBean getRoomById(Long id);

    void saveRoom(RoomBean user);

    void deleteRoom(Long id);
}
