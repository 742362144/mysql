package com.mysql.controller;

import com.mysql.serviceImpl.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Coder on 2017/7/23.
 */
@WebServlet(name = "PersonController", urlPatterns = { "/PersonController"})
public class PersonController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pid = Integer.parseInt(request.getParameter("pid"));
        PersonServiceImpl service = new PersonServiceImpl();
        if(service.reduceMoney(pid)){
            response.getWriter().print("success");
        }else{
            response.getWriter().print("false");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
