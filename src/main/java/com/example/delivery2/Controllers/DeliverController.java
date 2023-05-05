package com.example.delivery2.Controllers;

import com.example.delivery2.Services.Impl.ClientServiceImpl;
import com.example.delivery2.Services.Impl.ZakazServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/deliver")
@AllArgsConstructor
public class DeliverController {
    ZakazServiceImpl zakazService;
    ClientServiceImpl clientService;

    @GetMapping
    String deliver(Model model){
        model.addAttribute(clientService.currentUser().get());

        return "deliver";
    }



}
