package com.micro.microbusiness.controller;

import com.micro.microbusiness.dto.BusinessAreaDTO;
import com.micro.microbusiness.entity.BusinessArea;
import com.micro.microbusiness.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService service;

    @PostMapping()
    public BusinessAreaDTO addArea(@RequestBody BusinessAreaDTO area) {
        return service.addArea(BusinessArea.builder()
                .name(area.getName())
                .description(area.getDescription())
                .build());
    }

    @GetMapping()
    public List<BusinessAreaDTO> getAll() {
        return service.getAll();
    }
}
