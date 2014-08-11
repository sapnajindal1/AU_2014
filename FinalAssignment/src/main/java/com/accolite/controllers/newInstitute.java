package com.accolite.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.accolite.managebeans.*;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import antlr.collections.List;

/**
 * Servlet implementation class Connector
 */
@WebServlet("/Pages/Connector/*")
public class newInstitute extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ManageInstitute.class.getName());
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newInstitute() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String instTitle=request.getParameter("title");
		System.out.println("title"+instTitle);
		String description=request.getParameter("desr");
		String location= request.getParameter("loc");
		String branches=request.getParameter("branches");
		String imgurl=request.getParameter("fileName");
       ManageInstitute mi= new ManageInstitute();
       // String imgUrl=insertImage(request);
       mi.addInstitute(instTitle, description, location, branches, imgurl);
       response.sendRedirect("../index.jsp");
        //mi.addInstitute("gg", "jgj", "jgg", "ggfgfgf", "jjfgfg");
		//System.out.println("sapna jindal"+imgUrl);
		
                  
    }      
		
	}


