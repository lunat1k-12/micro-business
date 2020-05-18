package com.micro.microbusiness.controller;

import com.micro.microbusiness.entity.BusinessArea;
import com.micro.microbusiness.repository.BusinessAreaRepository;
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

    private final BusinessAreaRepository repo;

    @PostMapping()
    public BusinessArea addArea(@RequestBody BusinessArea area) {
        return repo.save(area);
    }

    @GetMapping()
    public List<BusinessArea> getAll() {
        return repo.findAll();
    }
}
