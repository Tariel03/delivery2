package com.example.delivery2.Controllers;
import java.util.List;

import com.example.delivery2.Enums.ZakazStatus;
import com.example.delivery2.Services.Impl.*;
import com.example.delivery2.models.Distributor;
import com.example.delivery2.models.Goods;
import com.example.delivery2.models.Zakaz;
import com.example.delivery2.models.ZakazGood;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class GoodsController {
    GoodsServiceImpl goodsService;
    ZakazGoodServiceImpl zakazGoodService;
    DistributorServiceImpl distributorService;
    ZakazServiceImpl zakazService;
    ClientServiceImpl clientService;



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
    @PostMapping("goods/zakaz/{goods_id}")
    public String addToZakaz(Model model,@PathVariable Long goods_id) throws Exception {
        Goods goods = goodsService.findById(goods_id);
        ZakazGood zakazGood = new ZakazGood();
        zakazGood.setGoods(goods);
        zakazGoodService.save(zakazGood);
        return "redirect:/api/v1/distributor/1";
    }

//    @PostMapping("goods/add/{id}")
//    public String goodsAdd(@PathVariable Long id) throws Exception {
//        Goods goods = goodsService.findById(id);
//        goods.setQuantity(goods.getQuantity()+1);
//        goodsService.save(goods);
//        zakazService.save(zakazService.findById(205L));
//        return "redirect:/api/v1/distributor/1";
//    }
//    @PostMapping("goods/minus/{id}")
//    public String goodsMinus(@PathVariable Long id) throws Exception {
//        Goods goods = goodsService.findById(id);
//        goods.setQuantity(goods.getQuantity()-1);
//        zakazService.save(zakazService.findById(205L));
//        goodsService.save(goods);
//        return "redirect:/api/v1/distributor/1";
//    }








}
