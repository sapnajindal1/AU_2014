package com.GroupOn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class EnterData
 */
@WebServlet("/EnterData")
public class EnterData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public EnterData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String formtype=request.getParameter("formtype");
		DataBaseHelper dbh = new DataBaseHelper();
		int type_form=0;
		if(formtype.equalsIgnoreCase("login"))
			type_form=1;
		else if(formtype.equalsIgnoreCase("signup"))
			type_form=2;
		else if(formtype.equalsIgnoreCase("home_page"))
			type_form=3;
		else if(formtype.equalsIgnoreCase("addoffer"))
			type_form=4;
		  
		  switch(type_form)
		  {
		  case 1 :
			  response.setContentType("application/json");
		        PrintWriter out = response.getWriter();
		   String email = request.getParameter("userName");
		   String password = request.getParameter("password");
			JSONObject jobj=null;
			try {
				jobj = dbh.verify_Credentials(email, password);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   out.print(jobj);
			   
		   break;
		   
		  case 2:
			  String fname=request.getParameter("FirstName");
			  String lname=request.getParameter("LastName");
			  String emailid=request.getParameter("EmailId");
			  String typeofuser=request.getParameter("mydropdown");
			  String adrs=request.getParameter("Address");
			  String pswrd=request.getParameter("password");
			 			  try {
			 				  dbh.insertUser(fname, lname, typeofuser, emailid, pswrd, adrs);		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   break;
		  case 3:
			  response.setContentType("application/json");
		        PrintWriter out1 = response.getWriter();
		        JSONArray offersObj=null;
				try {
					offersObj = dbh.createJsonForOffer();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        out1.print(offersObj);
				
			   break;
		  case 4:
			  String oname=request.getParameter("OfferName");
			  String desc=request.getParameter("OfferDescription");
			  String sdate=request.getParameter("StartDate");
			  String edate=request.getParameter("EndDate");
			  String nmbrofusers=request.getParameter("Number of Users");
			  String cat=request.getParameter("mydropdown");
			  String cost=request.getParameter("cost in rupees");
			  try {
				dbh.insertOffer(oname, desc, cat, sdate, edate, nmbrofusers, cost, 3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			   break;
		  
		  }
		// TODO Auto-generated method stub
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
