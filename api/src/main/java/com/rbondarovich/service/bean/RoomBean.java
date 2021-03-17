package com.rbondarovich.service.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomBean {

    private Long id;
    private String name;
    private Boolean bulb;
    private String country;

}
