package com.micro.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaValidationResponse implements Serializable {

    private BusinessAreaDTO businessArea;
    private boolean exists;
}
