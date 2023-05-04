package com.example.delivery2.Services;

import com.example.delivery2.Enums.ZakazStatus;
import com.example.delivery2.models.Client;
import com.example.delivery2.models.Goods;
import com.example.delivery2.models.Zakaz;

import java.util.List;

public interface ZakazService {
    List<Zakaz> findAll();
    List<Zakaz>findByGoods(Goods goods);
    Zakaz findById(Long id) throws Exception;
    Zakaz save(Zakaz zakaz);
    List<Zakaz>findByClient(Client client);
    List<Zakaz>findByZakazStatus(ZakazStatus zakazStatus);
    List<Zakaz>findByClientAndZakazStatusNotLike(Client client, ZakazStatus zakazStatus);



}
