package com.test.luizalabs.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {

    Long productId;
    double value;

}
