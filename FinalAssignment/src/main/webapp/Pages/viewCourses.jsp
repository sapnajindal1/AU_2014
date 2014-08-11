<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="com.accolite.managebeans.*"%>
<%@ page import="com.accolite.beans.*"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Institution</title>
<link rel="stylesheet" type="text/css"
	href="../Bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="../Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../Bootstrap/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css"
	href="../Bootstrap/css/bootstrap-theme.min.css">
</head>
<body style="background-color: antiquewhite;height: 500px">
<%
ManageCourse mc= new ManageCourse();
List courses= mc.getCourses();
%>

	<h1 align="center">
		<b>Courses </b>
	</h1>
				
				<div class="form-group">
    <label for="admisn">All Courses </label>
        <%  for (Iterator iterator = courses.iterator(); iterator.hasNext();) {
			Course cour = (Course) iterator.next();
			Institute inst=cour.getInst();
			int i=cour.getCourseid();%>
			</br>
            
            <label>Course Name : </label><i><%= cour.getCoursename()%></i>
            </br>
            <label>Course Description : </label><i><%= cour.getCoursedesc()%></i>
            </br>
            <label>Course duration : </label><i><%= cour.getDuration()%></i>
            </br>
            <label>Course Eligibility criteria : </label><i><%= cour.getEligibilitycriteria()%></i>
            </br>
            <label>Course Admission process : </label><i><%= cour.getAdmissionproc()%></i>
            </br>
            <a href="updateCourse.jsp?Id=<%= i %>" ><button type="button" class="btn btn-primary btn-lg" onclick="this.parentNode.href">Edit Course</button></a>
           
            </br>
        <% } %>
        
</body>
</html>
