package com.rbondarovich.web.api;

import com.rbondarovich.service.bean.RoomBean;
import com.rbondarovich.service.impl.RoomServiceImpl;
import com.rbondarovich.web.utils.RequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomServiceImpl roomService;
    private final RequestFilter requestFilter;

    @GetMapping
    public ResponseEntity<Iterable<RoomBean>> getAllRooms() {
        Iterable<RoomBean> rooms = roomService.getAllRooms();
        ResponseEntity<Iterable<RoomBean>> responce = new ResponseEntity<>(rooms, HttpStatus.OK);

        return responce;
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomBean> getRoomById(@PathVariable("roomId") Long roomId, HttpServletRequest request) {
        String ip = requestFilter.getRemoteIpFrom(request);
        RoomBean room = roomService.getRoomById(roomId, ip);
        ResponseEntity<RoomBean> result = new ResponseEntity<>(room, HttpStatus.OK);

        return result;
    }

    @PostMapping
    public ResponseEntity<RoomBean> saveRoom(@RequestBody RoomBean room) {
        roomService.saveRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Void> updateRoom(
            @PathVariable("roomId") Long roomId,
            @RequestBody RoomBean room,
            HttpServletRequest request) {

        String ip = requestFilter.getRemoteIpFrom(request);
        RoomBean roomFromDb = roomService.getRoomById(roomId, ip);
        roomFromDb.setName(room.getName());
        roomFromDb.setCountry(room.getCountry());
        roomFromDb.setBulb(room.getBulb());
        roomService.saveRoom(roomFromDb);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("roomId") Long roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
