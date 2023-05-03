package com.example.delivery2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDto {
    String name;
    String username;
//    String photo;
    String number;
}
