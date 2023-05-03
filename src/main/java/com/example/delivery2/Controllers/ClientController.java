package com.example.delivery2.Controllers;

import com.example.delivery2.Services.Impl.ClientServiceImpl;
import com.example.delivery2.dto.UpdateUserDto;
import com.example.delivery2.models.Client;
import com.example.delivery2.models.Ecard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/authenticated")
public class ClientController {
    ClientServiceImpl clientService;
    @GetMapping("/client")
    String personalPage(Model model){
        Client client = clientService.currentUser().get();
        model.addAttribute("client", client);
        return "personalPage";
    }
    @PostMapping("/client/edit/{client_id}")
    String editMe(@PathVariable Long client_id, @RequestParam String username, @RequestParam String name, @RequestParam String number){
        UpdateUserDto userDto = new UpdateUserDto(username,name,number);
        clientService.edit(userDto, clientService.findById(client_id));
        return "redirect:/auth/login";

    }
    @PostMapping("/add/card/{client_id}")
    String addCard(@RequestParam Long inn, @RequestParam  LocalDate localDate,@PathVariable Long client_id, @RequestParam String bankName){
        Ecard ecard = new Ecard(inn,localDate,bankName);
        ecard.setClient(clientService.currentUser().get());
        return "personalPage";
    }



}
