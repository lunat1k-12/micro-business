package com.micro.microbusiness.listener;

import com.micro.microbusiness.dto.BusinessAreaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BusinessListener {

    @RabbitListener(queues = "${business-queue}")
    public void processBusiness(BusinessAreaDTO dto) {
        log.info("Received from business: " + dto.getName());
    }

}
