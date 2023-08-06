package com.snva.springboot.bootcamp.controller.v1.response;

import com.snva.springboot.bootcamp.dto.model.user.UserDto;
import lombok.Data;

@Data
public class LoginResponse {
    private  String status;
    private  Object response;
    private UserDto user;
}
