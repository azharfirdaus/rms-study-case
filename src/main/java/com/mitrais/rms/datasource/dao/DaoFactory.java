package com.mitrais.rms.datasource.dao;

import com.mitrais.rms.datasource.dao.impl.CarDaoUsingArray;
import com.mitrais.rms.datasource.dao.impl.CarDaoUsingDatabase;

public class DaoFactory {

    private final CarDao carDaoUsingArray = new CarDaoUsingArray();
    private final CarDao carDaoUsingDatabase = new CarDaoUsingDatabase();

    public static Dao getCarDaoUsingArray(){
        return SingletonHelper.INSTANCE.carDaoUsingArray;
    }

    public static Dao getCarDaoUsingDatabase() { return SingletonHelper.INSTANCE.carDaoUsingDatabase; }

    private static class SingletonHelper{
        private static final DaoFactory INSTANCE = new DaoFactory();
    }

}
