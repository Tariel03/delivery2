package com.example.delivery2.dto;

import com.example.delivery2.Enums.CategoryGoods;
import com.example.delivery2.Enums.DeliveryWay;

import lombok.Data;

import javax.persistence.*;

@Data
public class GoodsDto {
    int quantity;
    @Enumerated(EnumType.STRING)
    CategoryGoods categoryGoods;
    @Enumerated(EnumType.STRING)
    DeliveryWay deliveryWay;
    String distributor;
}
