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

@WebServlet("/cars/*")
public class GetPostCarWithIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getPathInfo().split("/")[1]);
            CarDao carDao = (CarDao) DaoFactory.getCarDaoUsingDatabase();

            Car acquiredCar = carDao.findOne(id).orElse(null);

            forwardObjectOtherwiseRedirectToNotFound(acquiredCar, "/WEB-INF/car/update.jsp", request, response);

        } catch (NumberFormatException err){
            redirectToNotFound(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String registrationNo   = request.getParameter("registrationNo");
        String colour           = request.getParameter("colour");
        String method           = request.getParameter("method");

        try {
            Long id = Long.parseLong(request.getPathInfo().split("/")[1]);
            CarDao carDao = (CarDao) DaoFactory.getCarDaoUsingDatabase();

            if(method == null || method.equals("put")){
                Car updatedCar = carDao.update(new Car(id, registrationNo, colour));
                forwardObjectOtherwiseRedirectToNotFound(updatedCar, "/WEB-INF/car/car.jsp", request, response);
            } else if(method.equals("delete")) {
                carDao.delete(id);
                response.sendRedirect("http://localhost:8080/cars");
            }

        } catch (NumberFormatException err){
            redirectToNotFound(request, response);
        }
    }

    private void forwardObjectOtherwiseRedirectToNotFound(Car car,
                                                          String resource,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response )
                throws ServletException, IOException {

            if(car == null)
                redirectToNotFound(request, response);
            else {
                request.setAttribute("car", car);
                request.getRequestDispatcher(resource).forward(request, response);
            }
        }

        private void redirectToNotFound(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {

            request.getRequestDispatcher("/WEB-INF/car/no_car.jsp").forward(request, response);
        }
}
