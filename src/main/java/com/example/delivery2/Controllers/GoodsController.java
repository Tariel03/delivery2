package com.example.delivery2.Controllers;
import java.util.List;

import com.example.delivery2.Enums.ZakazStatus;
import com.example.delivery2.Services.Impl.ClientServiceImpl;
import com.example.delivery2.Services.Impl.DistributorServiceImpl;
import com.example.delivery2.Services.Impl.GoodsServiceImpl;
import com.example.delivery2.Services.Impl.ZakazServiceImpl;
import com.example.delivery2.models.Distributor;
import com.example.delivery2.models.Goods;
import com.example.delivery2.models.Zakaz;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1")
public class GoodsController {
    GoodsServiceImpl goodsService;
    DistributorServiceImpl distributorService;
    ZakazServiceImpl zakazService;
    ClientServiceImpl clientService;

    public GoodsController(GoodsServiceImpl goodsService, DistributorServiceImpl distributorService, ZakazServiceImpl zakazService, ClientServiceImpl clientService) {
        this.goodsService = goodsService;
        this.distributorService = distributorService;
        this.zakazService = zakazService;
        this.clientService = clientService;
    }

    @GetMapping("/goods/{id}")
    public String findById(Model model, @PathVariable Long id){
        model.addAttribute("goods",goodsService.findById(id));
        return "goodsById";
    }
    @PostMapping("/goods/save")
    public String save(@RequestBody Goods goods){
         goodsService.save(goods);
         return "redirect:/goods";
    }
    @GetMapping("/goods/{distributor_id})")
    public String goodsByDistributor(Model model,@PathVariable Long distributor_id){
        model.addAttribute("goods",goodsService.findByDistributor(distributorService.findById(distributor_id)));
        return "goods";
    }
    @PostMapping("goods/zakaz/{goods_id}/{zakaz_id}")
    public String addToZakaz(Model model,@PathVariable Long goods_id, @PathVariable Long zakaz_id) throws Exception {
        Zakaz zakaz = zakazService.findById(zakaz_id);
        Goods goods = goodsService.findById(goods_id);
        zakaz.getGoods().add(goods);
        goods.setZakaz(zakaz);
        goodsService.save(goods);
        zakaz.setClient(clientService.currentUser().get());
        zakaz.setZakazStatus(ZakazStatus.Received);
        zakazService.save(zakaz);
        return "redirect:/api/v1/distributor/1";
    }
    @PostMapping("goods/add/{id}")
    public String goodsAdd(@PathVariable Long id) throws Exception {
        Goods goods = goodsService.findById(id);
        goods.setQuantity(goods.getQuantity()+1);
        goodsService.save(goods);
        zakazService.save(zakazService.findById(205L));
        return "redirect:/api/v1/distributor/1";
    }
    @PostMapping("goods/minus/{id}")
    public String goodsMinus(@PathVariable Long id) throws Exception {
        Goods goods = goodsService.findById(id);
        goods.setQuantity(goods.getQuantity()-1);
        zakazService.save(zakazService.findById(205L));
        goodsService.save(goods);
        return "redirect:/api/v1/distributor/1";
    }








}
