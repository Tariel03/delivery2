package com.example.delivery2.Controllers;

import com.example.delivery2.Enums.Payment;
import com.example.delivery2.Services.Impl.DistributorServiceImpl;
import com.example.delivery2.Services.Impl.GoodsServiceImpl;
import com.example.delivery2.Services.Impl.ZakazGoodServiceImpl;
import com.example.delivery2.Services.Impl.ZakazServiceImpl;
import com.example.delivery2.models.Distributor;
import com.example.delivery2.models.Goods;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class DistributorController {
    DistributorServiceImpl distributorService;
    GoodsServiceImpl goodsService;
    ZakazGoodServiceImpl zakazGoodService;
    ZakazServiceImpl zakazService;


    @GetMapping("/distributor/all")
    public String  distributorList( Model model, @ModelAttribute Distributor distributor){
        List<Distributor> distributors = distributorService.findAll();
        model.addAttribute(distributors);
        System.out.println(distributors);
        return "distributor";
    }
    @GetMapping("/distributor/{id}")
    public String distributorById(Model model,@PathVariable Long id) throws Exception {
        Distributor distributor =distributorService.findById(id);
        AtomicInteger total = new AtomicInteger();
        distributor.getPoint().forEach(total::addAndGet);
        model.addAttribute("total",total.get() > 0 ? total.get()/distributor.getQuantity():1);
        model.addAttribute("distributor",distributor);
        model.addAttribute("goods", goodsService.findByDistributor(distributorService.findById(id)));
        model.addAttribute("zakazGoods",zakazGoodService.findByZakaz(null));
        System.out.println(distributorService.findById(id));
        model.addAttribute("payments",Payment.values());
        return "distributorById";
    }
    @PostMapping("/distributor/review/{id}")
    public String reviewDistributor(@PathVariable Long id, @RequestParam int review){
        Distributor distributor = distributorService.findById(id);
        distributor.getPoint().add(review);
        distributor.setQuantity(distributor.getPoint().size());
        distributorService.save(distributor);
        return "redirect:/api/v1/distributor/"+distributor.getId();
    }
    @PostMapping("/admin/distributor/create")
    public String createDistributor(@RequestParam String name, @RequestParam String address){
        Distributor distributor = new Distributor(name, address);
        distributorService.save(distributor);
        distributorService.save(distributor);
        return "redirect:/api/v1/admin";
    }




}
