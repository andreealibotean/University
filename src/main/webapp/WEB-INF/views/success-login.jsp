<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andreealibotean
  Date: 11/27/2015
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <link href="<c:url value="/resources/core/css/bootstrap.min.css" />" rel="stylesheet">
    <title>Welcome page</title>
</head>
<body>
<div class="container">
    <h1>Welcome page</h1>

    <p>You have successfully logged in.<br/>
        <a href="${pageContext.request.contextPath}/index.html" class="btn btn-info">Home page</a><br/></p>
</div>
</body>
</html>
