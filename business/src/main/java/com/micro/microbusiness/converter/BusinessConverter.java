package com.micro.microbusiness.converter;

import com.micro.microbusiness.dto.BusinessAreaDTO;
import com.micro.microbusiness.entity.BusinessArea;
import org.springframework.stereotype.Component;

@Component
public class BusinessConverter implements EntityConverter<BusinessArea, BusinessAreaDTO> {

    @Override
    public BusinessAreaDTO toDto(BusinessArea entity) {
        return BusinessAreaDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
