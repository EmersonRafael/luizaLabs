package com.test.luizalabs.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserDTO {

    Long idUser;
    String name;
    List<OrderDTO> orders;
}
