package com.accolite.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Iterator;
import java.io.File;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;

/**
 * Servlet implementation class imageUpload
 * @WebServlet("/Pages/uploadImage/*")
 */
public class imageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageUpload() {
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
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		 if (!isMultipart) {
		 } else {
		   FileItemFactory factory = new DiskFileItemFactory();
		   ServletFileUpload upload = new ServletFileUpload(factory);
		   List items = null;
		   try {
		   items = upload.parseRequest(request);
		   } catch (FileUploadException e) {
		   e.printStackTrace();
		    try {
		           String itemName = item.getName();
		   File savedFile = new File(getServletContext().getRealPath("/")+"images\\"+itemName);
		   item.write(savedFile);
		   System.out.println(emp_name+"sapna");
		    
		   } catch (Exception e) {
		   e.printStackTrace();
		   }
		   }
		   }
	}

}
