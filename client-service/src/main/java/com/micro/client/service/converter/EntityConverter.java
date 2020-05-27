package com.micro.client.service.converter;

import java.io.Serializable;

public interface EntityConverter<E, D extends Serializable> {

    D toDto(E entity);
}
