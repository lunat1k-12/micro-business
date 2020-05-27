package com.micro.client.service.controller;

import com.micro.client.service.dto.ClientDTO;
import com.micro.client.service.entity.Client;
import com.micro.client.service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping
    public List<ClientDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ClientDTO save(@RequestBody ClientDTO client) {
        return service.save(Client.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .businessAreaId(client.getBusinessAreaId())
                .build());
    }
}
