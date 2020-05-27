package com.micro.client.service.listener;

import com.micro.client.service.converter.ClientConverter;
import com.micro.client.service.repository.ClientRepository;
import com.micro.common.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientListener {
    private final ClientRepository repository;
    private final ClientConverter converter;

    @RabbitListener(queues = "${app.rabbit.active_queue}")
    public List<ClientDTO> getClients(List<Long> ids) {
        log.info("Search clients for: {}", ids);
         return repository.findAllByBusinessAreaIdIn(ids).stream()
                 .map(converter::toDto)
                 .collect(toList());
    }
}
