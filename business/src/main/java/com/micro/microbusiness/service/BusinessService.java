package com.micro.microbusiness.service;

import com.micro.microbusiness.converter.BusinessConverter;
import com.micro.microbusiness.dto.BusinessAreaDTO;
import com.micro.microbusiness.entity.BusinessArea;
import com.micro.microbusiness.repository.BusinessAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BusinessService {

    @Value("${app.rabbit.active_queue}")
    private String businessQueue;

    private final BusinessAreaRepository repo;
    private final AmqpTemplate template;
    private final BusinessConverter converter;

    public BusinessAreaDTO addArea(@RequestBody BusinessArea area) {
        var areaDto = converter.toDto(repo.save(area));
        template.convertAndSend(businessQueue, areaDto);
        return areaDto;
    }

    public List<BusinessAreaDTO> getAll() {
        return repo.findAll().stream()
                .map(converter::toDto)
                .collect(toList());
    }
}
