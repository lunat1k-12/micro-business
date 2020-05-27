package com.micro.client.service.service;

import com.micro.client.service.converter.ClientConverter;
import com.micro.client.service.entity.Client;
import com.micro.client.service.exception.ClientException;
import com.micro.client.service.repository.ClientRepository;
import com.micro.common.dto.AreaValidationResponse;
import com.micro.common.dto.BusinessAreaDTO;
import com.micro.common.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Value("${app.rabbit.business_queue}")
    private String businessQueue;

    private final ClientRepository repository;
    private final ClientConverter converter;
    private final AmqpTemplate template;

    public List<ClientDTO> getAll() {
        return repository.findAll().stream()
                .map(converter::toDto)
                .collect(toList());
    }

    public ClientDTO save(Client client) {
        this.validateBusinessArea(client);
        return converter.toDto(repository.save(client));
    }

    private void validateBusinessArea(Client client) {
        AreaValidationResponse resp = (AreaValidationResponse) template.convertSendAndReceive(businessQueue, BusinessAreaDTO.builder()
                .id(client.getBusinessAreaId())
                .build());

        if (!Optional.ofNullable(resp)
                .map(AreaValidationResponse::isExists)
                .orElse(false)) {
            throw new ClientException(String.format("Invalid business area id: %s", client.getBusinessAreaId()));
        }
    }
}
