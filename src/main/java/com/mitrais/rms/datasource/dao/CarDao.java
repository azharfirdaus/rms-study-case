package com.mitrais.rms.datasource.dao;

import com.mitrais.rms.datasource.entity.Car;
import java.util.List;
import java.util.Optional;

public interface CarDao extends Dao<Car, Long> {

    Optional<Car> findCarByRegistrationNo(String registrationNo);

    Optional<List<Car>> findCarByColour(String colour);

    Optional<Car> findCarByRegistrationNoAndColour(String registrationNo, String colour);
}
