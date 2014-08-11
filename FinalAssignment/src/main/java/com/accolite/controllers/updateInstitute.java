package com.accolite.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accolite.managebeans.ManageInstitute;

/**
 * Servlet implementation class updateInstitute
 */
@WebServlet("/Pages/updateInstitute/*")
public class updateInstitute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateInstitute() {
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
		String instTitle=request.getParameter("title");
		System.out.println("title"+instTitle);
		String description=request.getParameter("desr");
		String location= request.getParameter("loc");
		String branches=request.getParameter("branches");
		String imgurl=request.getParameter("fileName");
       ManageInstitute mi= new ManageInstitute();
       // String imgUrl=insertImage(request);
       mi.updateInstitute(id,instTitle, description, location, branches, imgurl);
       response.sendRedirect("../index.jsp");
	}

}
