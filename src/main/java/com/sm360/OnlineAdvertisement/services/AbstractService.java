package com.sm360.OnlineAdvertisement.services;

import java.util.List;
import java.util.UUID;

public interface AbstractService <T>{
    T create(T object);
    T update(UUID id, T object);
    T findById(UUID id);
    List<T> findAll();
}
