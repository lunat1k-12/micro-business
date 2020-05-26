package com.micro.microbusiness.converter;

import java.io.Serializable;

public interface EntityConverter<E, D extends Serializable> {

    D toDto(E entity);
}
