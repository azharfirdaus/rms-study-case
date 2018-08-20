package com.mitrais.rms.datasource.dao.impl;

import com.mitrais.rms.datasource.ConnectionFactory;
import com.mitrais.rms.datasource.dao.CarDao;
import com.mitrais.rms.datasource.entity.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoUsingDatabase implements CarDao {

    @Override
    public Optional<Car> findCarByRegistrationNo(String registrationNo) {
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(" SELECT * FROM cars WHERE registration_no = ? ");
            statement.setString(1, registrationNo);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Car car = new Car(resultSet.getLong("id"),
                        resultSet.getString("registration_no"),
                        resultSet.getString("colour"));
                return Optional.of(car);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Car>> findCarByColour(String colour) {
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement
                    = connection.prepareStatement(" SELECT * FROM cars WHERE colour = ? ");

            statement.setString(1, colour);
            ResultSet resultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while(resultSet.next()){
                cars.add(new Car(resultSet.getLong("id"),
                        resultSet.getString("registration_no"),
                        resultSet.getString("colour")));
            }
            return Optional.of(cars);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Car> findCarByRegistrationNoAndColour(String registrationNo, String colour) {
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement
                    = connection.prepareStatement(" SELECT * FROM cars WHERE registration_no = ? AND colour = ? ");

            statement.setString(1, registrationNo);
            statement.setString(2, colour);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Car car = new Car(resultSet.getLong("id"),
                        resultSet.getString("registration_no"),
                        resultSet.getString("colour"));

                return Optional.of(car);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Car> findOne(Long id) {
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement
                    = connection.prepareStatement(" SELECT * FROM cars WHERE id = ? ");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Car car = new Car(resultSet.getLong("id"),
                        resultSet.getString("registration_no"),
                        resultSet.getString("colour"));
                return Optional.of(car);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Car>> findAll() {
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(" SELECT * FROM cars ");
            ResultSet resultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while(resultSet.next()){
                cars.add(new Car(resultSet.getLong("id"),
                        resultSet.getString("registration_no"),
                        resultSet.getString("colour")));
            }
            return Optional.of(cars);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Car save(Car car) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cars VALUES (NULL, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, car.getRegistrationNo());
            statement.setString(2, car.getColour());
            int respond = statement.executeUpdate();
            if(respond == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                return (resultSet.next()) ? findOne(resultSet.getLong(1)).orElse(null) : null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Car update(Car car) {
        try (Connection connection = ConnectionFactory.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(" UPDATE cars SET registration_no= ? , colour = ? WHERE id = ? ");
            statement.setString(1, car.getRegistrationNo());
            statement.setString(2, car.getColour());
            statement.setLong(3, car.getId());
            int respond = statement.executeUpdate();
            if(respond == 1) {
                return Optional.of(car).orElse(null);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Car delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(" DELETE FROM cars WHERE id=? ");
            statement.setLong(1, id);
            int respond = statement.executeUpdate();
            if(respond == 1) {
                return findOne(id).orElse(null);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
