package com.example.delivery2.Services;

import com.example.delivery2.models.Ecard;
import com.example.delivery2.models.Goods;

import java.util.List;

public interface EcardService {

    Ecard addCard(Ecard ecard);
    Ecard addMoney(int money, Long id);
    Ecard minusMoney(List<Goods> goodsList, Long id);
    void deleteCard(Long id);
    Ecard findById(Long id);


}
