package com.example.delivery2.repositories;

import com.example.delivery2.Enums.ZakazStatus;
import com.example.delivery2.models.Client;
import com.example.delivery2.models.Goods;
import com.example.delivery2.models.Zakaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZakazRepository extends JpaRepository<Zakaz, Long> {
    List<Zakaz>findByClient(Client client);
    List<Zakaz>findByZakazStatus(ZakazStatus zakazStatus);
    List<Zakaz>findByClientAndZakazStatusNotLike(Client client, ZakazStatus zakazStatus);

}
