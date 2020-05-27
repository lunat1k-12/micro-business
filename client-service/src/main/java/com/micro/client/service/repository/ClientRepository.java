package com.micro.client.service.repository;

import com.micro.client.service.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAllByBusinessAreaIdIn(List<Long> areas);
}
