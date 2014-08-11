<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="com.accolite.managebeans.*"%>
<%@ page import="com.accolite.beans.*"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
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
<body style="background-color: antiquewhite;height: 500px" >
<%
ManageInstitute mi= new ManageInstitute();
List insts= mi.getInstitutes();
String ss =request.getParameter("Id");
int i= Integer.parseInt(ss);
%>
<div >
	<h1 align="center">
		<b>Course Information</b>
	</h1>

	<form action="updateCourse?Id=<%= i %>" method="post">
		<div class="form-group">
			<label for="cname">Course Name :</label> <input type="text" id="title"
				placeholder="Course Name" name="cname">
		</div>
		<div class="form-group">
			<label for="cdesc">Description Of the Course :</label> <input
				type="text" id="desc" placeholder="Description" name="desr">
		</div>
		<div class="form-group">
			<label for="duration">Duration :</label> <input type="text" id="loc"
				placeholder="Duration of the Course" name="dur">
		</div>
		<div class="form-group">
			<label for="eligibility">Eligibility Criteria :</label> <input type="text" id="loc"
				placeholder="Eligibility Criteria" name="ecriteria">
		</div>
		</div>
		<div class="form-group">
			<label for="admisn">Admission Process :</label> <input type="text" id="admsn"
				placeholder="Admission Process" name="aprocess">
				</br>
				</br>
				
				<div class="form-group">
    <label for="admisn">select Institute :</label>
        <select name="instName">
        <%  for (Iterator iterator = insts.iterator(); iterator.hasNext();) {
			Institute inst = (Institute) iterator.next(); %>
            <option><%= inst.getTitle()%></option>
        <% } %>
        </select>
        </div>

			<button type="submit" class="btn btn-default">Update</button>
</div>	
	</form>
</body>
</html>
