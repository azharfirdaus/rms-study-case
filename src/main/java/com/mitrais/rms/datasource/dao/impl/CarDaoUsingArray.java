package com.mitrais.rms.datasource.dao.impl;

import com.mitrais.rms.datasource.dao.CarDao;
import com.mitrais.rms.datasource.entity.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarDaoUsingArray implements CarDao {

    private List<Car> dataSourceOfCars;

    public CarDaoUsingArray(){
        dataSourceOfCars
                = Stream.of(new Car(new Long(1), "B 1345 XG", "Black"),
                            new Car(new Long(2), "B 6795 FG", "White"),
                            new Car(new Long(3), "B 9345 JK", "Purple"),
                            new Car(new Long(4), "B 7698 GH", "Red"),
                            new Car(new Long(5), "B 8205 GH", "Brown"))
                        .collect(Collectors.toList());
    }


    @Override
    public Optional<List<Car>> findAll() {
        List<Car> cars = dataSourceOfCars.stream().collect(Collectors.toCollection(ArrayList::new));
        return Optional.of(cars);
    }

    @Override
    public Optional<Car> findOne(Long id) {
        return dataSourceOfCars.stream()
                            .filter( car -> car.getId().equals(id))
                            .findAny();
    }
    @Override
    public Optional<Car> findCarByRegistrationNo(String registrationNo) {
        return dataSourceOfCars.stream()
                            .filter(car -> car.getRegistrationNo().equals(registrationNo))
                            .findFirst();
    }

    @Override
    public Optional<List<Car>> findCarByColour(String colour) {
        List<Car> cars = dataSourceOfCars.stream()
                .filter(car -> car.getColour().equals(colour))
                .collect(Collectors.toCollection(ArrayList::new));
        return Optional.of(cars);
    }

    @Override
    public Optional<Car> findCarByRegistrationNoAndColour(String registrationNo, String colour) {
        return dataSourceOfCars.stream()
                .filter(car -> car.getRegistrationNo().equals(registrationNo))
                .filter(car -> car.getColour().equals(colour))
                .findFirst();
    }

    @Override
    public Car save(Car o) {
        throw new UnsupportedOperationException("not support this operation yet");
    }

    @Override
    public Car update(Car o) {
        throw new UnsupportedOperationException("not support this operation yet");
    }

    @Override
    public Car delete(Long id) {
        throw new UnsupportedOperationException("not support this operation yet");
    }
}
