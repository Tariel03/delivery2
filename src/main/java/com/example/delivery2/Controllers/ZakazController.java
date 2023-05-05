package com.example.delivery2.Controllers;

import com.example.delivery2.Enums.Payment;
import com.example.delivery2.Enums.ZakazStatus;
import com.example.delivery2.Services.Impl.ClientServiceImpl;
import com.example.delivery2.Services.Impl.ZakazGoodServiceImpl;
import com.example.delivery2.Services.Impl.ZakazServiceImpl;
import com.example.delivery2.models.Zakaz;
import com.example.delivery2.models.ZakazGood;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/api/v1/authenticated")
@AllArgsConstructor
public class ZakazController {
    ZakazServiceImpl zakazService;
    ZakazGoodServiceImpl zakazGoodService;
    ClientServiceImpl clientService;
    @GetMapping("/zakaz/all")
    public String allZakazs(Model model){
        model.addAttribute("zakazList",zakazService.findByClientAndZakazStatusNotLike(clientService.currentUser().get(),ZakazStatus.Declined));
        return "bin";
    }
    @PostMapping("/zakaz/dead/{id}")
    public String killZakaz(Model model, @PathVariable Long id) throws Exception {
        Zakaz zakaz = zakazService.findById(id);
        zakaz.setZakazStatus(ZakazStatus.Declined);
        zakazService.save(zakaz);
        return "redirect:/api/v1/authenticated/zakaz/all";
    }
    @PostMapping("zakaz/make")
    public String makeZakaz(@RequestParam String address){
        Zakaz zakaz = new Zakaz();
        AtomicInteger total = new AtomicInteger();
        List<ZakazGood> zakazGoods =zakazGoodService.findByZakaz(null);
        zakazGoods.forEach(zakazGood -> {
            total.addAndGet(zakazGood.getGoods().getPrice());
        });
        zakazGoods.forEach(zakazGood -> {
            zakazGood.setZakaz(zakaz);
        });
        for (ZakazGood z : zakazGoods
        ) {
            zakazGoodService.save(z);
        }
        zakaz.setAddress(address);
        zakaz.setPayment(Payment.Cash);
        zakaz.setTotalPrice(total.get());
        zakaz.setClient(clientService.currentUser().get());
        zakaz.setQuantity(zakazGoods.size());
        zakaz.setZakazStatus(ZakazStatus.Received);
        zakazService.save(zakaz);
        return "redirect:/api/v1/authenticated/client";

    }





}
