package com.micro.microbusiness.service;

import com.micro.common.dto.BusinessAreaDTO;
import com.micro.common.dto.ClientDTO;
import com.micro.microbusiness.converter.BusinessConverter;
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

    @Value("${app.rabbit.client_queue}")
    private String clientsQueue;
    private final BusinessAreaRepository repo;
    private final BusinessConverter converter;
    private final AmqpTemplate template;

    public BusinessAreaDTO addArea(@RequestBody BusinessArea area) {
        return converter.toDto(repo.save(area));
    }

    public List<BusinessAreaDTO> getAll() {
        List<BusinessAreaDTO> areas = repo.findAll().stream()
                .map(converter::toDto)
                .collect(toList());

        List<ClientDTO> resp = (List<ClientDTO>) template.convertSendAndReceive(clientsQueue, areas.stream()
                .map(a -> a.getId())
                .collect(toList()));

        areas.forEach(area -> area.setClients(resp.stream()
                .filter(c -> c.getBusinessAreaId().equals(area.getId()))
                .collect(toList())));

        return areas;
    }
}
