package com.example.delivery2.Services.Impl;

import com.example.delivery2.Enums.ZakazStatus;
import com.example.delivery2.Services.ZakazService;
import com.example.delivery2.models.Client;
import com.example.delivery2.models.Goods;
import com.example.delivery2.models.Zakaz;
import com.example.delivery2.repositories.ZakazRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class ZakazServiceImpl implements ZakazService {
    ZakazRepository zakazRepository;
    @Override
    public List<Zakaz> findAll() {
        return zakazRepository.findAll();
    }

    @Override
    public List<Zakaz> findByGoods(Goods goods) {
        return zakazRepository.findByGoods(goods);
    }

    @Override
    public Zakaz findById(Long id) throws Exception {
        Optional<Zakaz> optionalZakaz = zakazRepository.findById(id);
        if(optionalZakaz.isEmpty()){
            throw new Exception("No such zakaz");
        }
        return optionalZakaz.get();
    }
    @Override
    public Zakaz save(Zakaz zakaz) {
        AtomicInteger total = new AtomicInteger();

        zakaz.getGoods().forEach(goods -> {
            total.addAndGet(goods.getPrice()* goods.getQuantity());
        });
        zakaz.setTotalPrice(total.get()+65);
        zakaz.setDeliveryPrice(65);
        zakaz.setQuantity(zakaz.getGoods().size());
        return zakazRepository.save(zakaz);
    }

    @Override
    public List<Zakaz> findByClient(Client client) {
        return zakazRepository.findByClient(client);
    }

    @Override
    public List<Zakaz> findByZakazStatus(ZakazStatus zakazStatus) {
        return zakazRepository.findByZakazStatus(zakazStatus);
    }
}
