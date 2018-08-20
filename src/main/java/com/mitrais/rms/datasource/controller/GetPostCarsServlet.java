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
import java.util.Map;

@WebServlet("/cars")
public class GetPostCarsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CarDao carDao               = (CarDao) DaoFactory.getCarDaoUsingDatabase();

        String registrationNoParam  = request.getParameter("registrationNo");
        String colourParam          = request.getParameter("colour");

        if(registrationNoParam == null && colourParam == null) {
            List<Car> acquiredCar = carDao.findAll().get();
            forwardObjects(acquiredCar, request, response);
        } else if(registrationNoParam != null && colourParam != null){
            Car acquiredCar = carDao.findCarByRegistrationNoAndColour(registrationNoParam, colourParam)
                                    .orElse(null);

            forwardObjectOtherwiseRedirectToNotFound(acquiredCar, request, response);
        } else if(registrationNoParam != null && colourParam == null){
            Car acquiredCar = carDao.findCarByRegistrationNo(registrationNoParam)
                                    .orElse(null);
            forwardObjectOtherwiseRedirectToNotFound(acquiredCar, request, response);
        } else if(registrationNoParam == null && colourParam != null){
            List<Car> acquiredCars  = carDao.findCarByColour(colourParam).get();
            forwardObjects(acquiredCars, request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("<html><body>bad request!!</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String registrationNo   = request.getParameter("registrationNo");
        String colour           = request.getParameter("colour");
        CarDao carDao           = (CarDao) DaoFactory.getCarDaoUsingDatabase();
        Car addedCar            = carDao.save(new Car(registrationNo, colour));

        forwardObjectOtherwiseRedirectToNotFound(addedCar, request, response);
    }

    private void forwardObjectOtherwiseRedirectToNotFound(Car car,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response )
                throws ServletException, IOException {

            if(car == null)
                redirectToNotFound(request, response);
            else {
                request.setAttribute("car", car);
                request.getRequestDispatcher("/WEB-INF/car/car.jsp").forward(request, response);
            }
        }

        private void forwardObjects(List<Car> cars,
                                    HttpServletRequest request,
                                    HttpServletResponse response )

                throws IOException, ServletException {

            request.setAttribute("cars", cars );
            request.getRequestDispatcher("/WEB-INF/car/cars.jsp").forward(request, response);
        }

        private void redirectToNotFound(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {
            request.getRequestDispatcher("/WEB-INF/car/no_car.jsp").forward(request, response);
        }
}
