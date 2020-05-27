package com.micro.client.service.converter;

import com.micro.client.service.dto.ClientDTO;
import com.micro.client.service.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter implements EntityConverter<Client, ClientDTO>{

    @Override
    public ClientDTO toDto(Client entity) {
        return ClientDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .businessAreaId(entity.getBusinessAreaId())
                .build();
    }
}
