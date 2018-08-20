package com.mitrais.rms.datasource.controller;

import com.mitrais.rms.datasource.dao.CarDao;
import com.mitrais.rms.datasource.dao.DaoFactory;
import com.mitrais.rms.datasource.entity.Car;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/cars/*")
public class GetCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getPathInfo().split("/")[1]);
            CarDao carDao = (CarDao) DaoFactory.getCarDaoUsingDatabase();

            Car acquiredCar = carDao.findOne(id).orElse(null);

            forwardObjectOtherwiseRedirectToNotFound(acquiredCar, request, response);

        } catch (NumberFormatException err){
            redirectToNotFound(request, response);
        }
    }

    private void forwardObjectOtherwiseRedirectToNotFound(Car car, HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            if(car == null)
                redirectToNotFound(request, response);
            else {
                request.setAttribute("car", car);
                request.getRequestDispatcher("/WEB-INF/car/car.jsp").forward(request, response);
            }
        }

        private void redirectToNotFound(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {
            request.getRequestDispatcher("/WEB-INF/car/no_car.jsp").forward(request, response);
        }
}
