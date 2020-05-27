package com.micro.microbusiness.service;

import com.micro.common.dto.BusinessAreaDTO;
import com.micro.microbusiness.converter.BusinessConverter;
import com.micro.microbusiness.entity.BusinessArea;
import com.micro.microbusiness.repository.BusinessAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessAreaRepository repo;
    private final BusinessConverter converter;

    public BusinessAreaDTO addArea(@RequestBody BusinessArea area) {
        return converter.toDto(repo.save(area));
    }

    public List<BusinessAreaDTO> getAll() {
        return repo.findAll().stream()
                .map(converter::toDto)
                .collect(toList());
    }
}
