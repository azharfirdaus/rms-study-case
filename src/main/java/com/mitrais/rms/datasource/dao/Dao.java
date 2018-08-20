package com.mitrais.rms.datasource.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {

    Optional<T> findOne(ID id);

    Optional<List<T>> findAll();

    T save(T o);

    T update(T o);

    T delete(ID id);
}
