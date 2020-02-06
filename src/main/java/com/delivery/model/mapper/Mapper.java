package com.delivery.model.mapper;

public interface Mapper<E, T> {

     E mapToEntity(T t);

     T mapToDomain(E e);
}
