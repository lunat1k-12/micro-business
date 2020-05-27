package com.micro.microbusiness.listener;

import com.micro.microbusiness.converter.BusinessConverter;
import com.micro.common.dto.AreaValidationResponse;
import com.micro.common.dto.BusinessAreaDTO;
import com.micro.microbusiness.repository.BusinessAreaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BusinessListener {
    private final BusinessAreaRepository repository;
    private final BusinessConverter converter;

    @RabbitListener(queues = "${app.rabbit.active_queue}")
    public AreaValidationResponse processBusiness(BusinessAreaDTO dto) {
        log.info("Received to validate: " + dto.getId());

        if (dto.getId() == null) {
            return AreaValidationResponse.builder()
                    .exists(false)
                    .build();
        }

        return repository.findById(dto.getId())
                .map(area -> AreaValidationResponse.builder()
                        .businessArea(converter.toDto(area))
                        .exists(true)
                        .build())
                .orElse(AreaValidationResponse.builder()
                        .exists(false)
                        .build());
    }

}
