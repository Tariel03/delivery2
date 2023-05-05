package com.example.delivery2.Controllers;

import com.example.delivery2.Enums.RequestStatus;
import com.example.delivery2.Enums.Roles;
import com.example.delivery2.Services.Impl.ClientServiceImpl;
import com.example.delivery2.Services.Impl.DistributorServiceImpl;
import com.example.delivery2.Services.Impl.GoodsServiceImpl;
import com.example.delivery2.Services.Impl.RequestServiceImpl;
import com.example.delivery2.models.Client;
import com.example.delivery2.models.Request;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    ClientServiceImpl clientService;
    RequestServiceImpl requestService;
    DistributorServiceImpl distributorService;
    GoodsServiceImpl goodsService;
    @GetMapping
    public String admin(Model model){
        model.addAttribute("client",clientService.currentUser().get());
        model.addAttribute("requests", requestService.findByRequestStatus(RequestStatus.Considered));
        model.addAttribute("distributors", distributorService.findAll());
        model.addAttribute("goodsService",goodsService);
        return "admin/admin";
    }
    @PostMapping("/request/approve/{id}")
    public String requestApprove(@PathVariable Long id){
        Request request = requestService.findById(id);
        request.setRequestStatus(RequestStatus.Approved);
        Client client = request.getClient();
        client.setRoles(Roles.ROLE_DELIVER);
        requestService.save(request);
        clientService.save(client);
        return "redirect:/api/v1/admin";
    }
    @PostMapping("/request/decline/{id}")
    public String requestDecline(@PathVariable Long id){
        Request request = requestService.findById(id);
        request.setRequestStatus(RequestStatus.Declined);
        requestService.save(request);
        return "redirect:/api/v1/admin";
    }
    
}
