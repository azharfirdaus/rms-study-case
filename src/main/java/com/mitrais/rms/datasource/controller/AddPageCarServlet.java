package com.mitrais.rms.datasource.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Azhar Firdaus
 * @email azhar.firdaus9@gmail.com
 * @created 19-Aug-18 1:01 PM
 */

@WebServlet("/add-car")
public class AddPageCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/car/add.jsp").forward(request, response);
    }


}
