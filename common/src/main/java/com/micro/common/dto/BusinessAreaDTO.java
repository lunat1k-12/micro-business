package com.micro.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessAreaDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private List<ClientDTO> clients;
}
