package com.shopbeafoodh.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById();

    T save(T t);

    void remove(Long id);
}
