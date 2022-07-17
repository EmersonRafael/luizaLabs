package com.test.luizalabs.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class OrderDTO {

    Long orderId;
    double total;
    Date date;
    List<ProductDTO> products;

}
