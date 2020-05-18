package com.micro.microbusiness.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "business_area")
@Data
public class BusinessArea {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
