package com.rbondarovich.service.impl;

import com.rbondarovich.dao.entity.Room;
import com.rbondarovich.dao.repository.RoomRepository;
import com.rbondarovich.service.bean.RoomBean;
import com.rbondarovich.service.exception.ResourceNotFoundException;
import com.rbondarovich.service.exception.WrongRoomException;
import com.rbondarovich.service.interfaces.RoomService;
import com.rbondarovich.service.utils.LocationFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final EntityBeanConverterImpl converter;
    private final RoomRepository roomRepository;
    private final LocationFinder locationFinder;

    @Override
    public Iterable<RoomBean> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomBean> roomBeans = converter.convertToBeanList(rooms, RoomBean.class);

        return roomBeans;
    }

    @Override
    public RoomBean getRoomById(Long id, String ip) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not exist with id: " + id));
        RoomBean roomBean = converter.convertToBean(room, RoomBean.class);

        if (!checkingAccessToRoom(roomBean, ip)) {
            throw new WrongRoomException("You can't enter the room which place in another country");
        }

        return roomBean;
    }

    @Override
    public void saveRoom(RoomBean roomBean) {
        Room room = converter.convertToEntity(roomBean, Room.class);
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    private Boolean checkingAccessToRoom(RoomBean room, String ip) {

//        Set<String> countryNames = locationFinder.getCountryByIp(ip);
        Set<String> countryNames = locationFinder.getCountryByIp("37.214.49.20");
        for (String countryName : countryNames) {
            if (countryName.equals(room.getCountry())) {
                return true;
            }
        }
        return false;
    }
}
