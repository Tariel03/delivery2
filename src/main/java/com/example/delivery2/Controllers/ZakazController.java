package com.example.delivery2.Controllers;

import com.example.delivery2.Enums.ZakazStatus;
import com.example.delivery2.Services.Impl.ClientServiceImpl;
import com.example.delivery2.Services.Impl.RegistrationServiceImpl;
import com.example.delivery2.Services.Impl.ZakazServiceImpl;
import com.example.delivery2.Services.RegistrationService;
import com.example.delivery2.models.Zakaz;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/authenticated")
@AllArgsConstructor
public class ZakazController {
    ZakazServiceImpl zakazService;
    ClientServiceImpl clientService;
    @GetMapping("/zakaz/all")
    public String allZakazs(Model model){
        model.addAttribute("zakazList",zakazService.findByClient(clientService.currentUser().get()));
        return "bin";
    }
    @PostMapping("/zakaz/dead/{id}")
    public String killZakaz(Model model, @PathVariable Long id) throws Exception {
        Zakaz zakaz = zakazService.findById(id);
        zakaz.setZakazStatus(ZakazStatus.Declined);
        zakazService.save(zakaz);
        return "redirect:/api/v1/authenticated/zakaz/all";
    }



}
