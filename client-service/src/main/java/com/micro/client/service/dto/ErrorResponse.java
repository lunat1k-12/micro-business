package com.micro.client.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    @Singular
    private List<ErrorMessage> clientErrors;
    @Singular
    private List<ErrorMessage> serverErrors;
}
