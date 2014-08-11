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
<body>
<%
ManageInstitute mi= new ManageInstitute();
List insts= mi.getInstitutes();
%>

	<h1 align="center">
		<b>Institutes </b>
	</h1>
				
				<div class="form-group">
    <label for="admisn">All Institutes :</label>
        <%  for (Iterator iterator = insts.iterator(); iterator.hasNext();) {
			Institute inst = (Institute) iterator.next();
			int i= inst.getInstid();%>
			</br>
            <label for="iname">Institute Name :</label><i> <%= inst.getTitle()%></i>
            </br>
            <label>Institute Description : </label><i><%= inst.getDesc()%></i>
            </br>
            <label>Institute Location : </label><i><%= inst.getLocation()%></i>
            </br>
            <label>Institute Branches : </label><i><%= inst.getBranches()%></i>
            </br>
            <a href="updateInstitute.jsp?Id=<%= i %>" ><button type="button" class="btn btn-primary btn-lg" onclick="this.parentNode.href">Edit Institute</button></a>
           
        <% } %>
        
</body>
</html>
