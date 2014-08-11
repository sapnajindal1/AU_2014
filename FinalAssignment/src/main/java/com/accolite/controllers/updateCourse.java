package com.accolite.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accolite.managebeans.ManageCourse;

/**
 * Servlet implementation class updateCourse
 */
@WebServlet("/Pages/updateCourse/*")
public class updateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ss =request.getParameter("Id");
		int id= Integer.parseInt(ss);
		String cname=request.getParameter("cname");
		String description=request.getParameter("desr");
		String Duration= request.getParameter("dur");
		int dur=Integer.parseInt(Duration);
		String criteria=request.getParameter("ecriteria");
		String aprocess=request.getParameter("aprocess");
		String instname=request.getParameter("instName");
       ManageCourse mc= new ManageCourse();
       // String imgUrl=insertImage(request);
       mc.updateCourse(id, cname, description, dur, criteria, aprocess,instname);
       response.sendRedirect("../index.jsp");
	}

}
