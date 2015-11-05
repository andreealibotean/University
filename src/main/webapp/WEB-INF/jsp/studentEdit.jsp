<%--
  Created by IntelliJ IDEA.
  User: Lei Florin
  Date: 28.09.2015
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit student</title>
        <link href="<c:url value="/resources/core/css/bootstrap.min.css" />" rel="stylesheet">
    </head>

<body>
<div class="container">

    <div class="navbar-header">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
            <li><a href="<%=request.getContextPath()%>/universities/faculties">Faculties</a></li>
            <li><a href="<%=request.getContextPath()%>/universities/students">Students</a></li>
            <li><a href="<%=request.getContextPath()%>/universities/courses">Courses</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="page-header">
                <h1 id="forms">Edit student</h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6">
            <div class="well bs-component">
                <form:form  method="post" modelAttribute="student" class="form-horizontal">
                    <fieldset>
                        <legend>Edit student</legend>

                            <%--Faculty--%>
                        <div class="form-group">
                            <label class="col-lg-4 control-label">Faculty:</label>
                            <div class="col-lg-8">
                                <div class="radio">
                                    <label>
                                            ${student.faculty.name}
                                    </label>
                                </div>

                            </div>
                        </div>

                            <%--Name--%>
                        <div class="form-group">
                            <label class="col-lg-4 control-label">Name</label>
                            <div class="col-lg-8">
                                <div class="radio">
                                    <form:input type="text" path="name" placeholder="Name" value="${student.name}"/>
                                </div>
                                <div class="has-error" style="color: #ff0700">
                                    <form:errors path="name"/>
                                </div>
                            </div>
                        </div>

                            <%--Surname--%>
                        <div class="form-group">
                            <label class="col-lg-4 control-label">Surname</label>
                            <div class="col-lg-8">
                                <div class="radio">
                                    <form:input type="text" path="surname" placeholder="Surname" value="${student.surname}"/>
                                </div>
                                <div class="has-error" style="color: #ff0700">
                                    <form:errors path="surname"/>
                                </div>
                            </div>
                        </div>

                            <%--Date of birth--%>
                        <div class="form-group">
                            <label class="col-lg-4 control-label">Date of birth</label>
                            <div class="col-lg-8">
                                <div class="radio">
                                    <form:input type="text" path="birth_date" placeholder="1990-12-31" value="${student.birth_date}"/>
                                </div>
                                <div class="has-error" style="color: #ff0700">
                                    <form:errors path="birth_date"/>
                                </div>
                            </div>
                        </div>

                            <%--Sex--%>
                        <div class="form-group">
                            <label class="col-lg-4 control-label">Sex</label>
                            <div class="col-lg-8">
                                <div class="radio">
                                    <form:input type="text" path="sex" placeholder="M/F" value="${student.sex}"/>
                                </div>
                                <div class="has-error" style="color: #ff0700">
                                    <form:errors path="sex"/>
                                </div>
                            </div>
                        </div>

                            <%--CNP--%>
                        <div class="form-group">
                            <label class="col-lg-4 control-label">CNP</label>
                            <div class="col-lg-8">
                                <div class="radio">
                                    <form:input type="text" path="CNP" placeholder="1910206025891" value="${student.CNP}"/>
                                </div>
                                <div class="has-error" style="color: #ff0700">
                                    <form:errors path="CNP"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-6 col-lg-offset-6">
                                <button type="submit" class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-default">Cancel</button>
                                <a class="btn btn-default" href="<%=request.getContextPath()%>/universities/students">Back</a>
                            </div>
                        </div>

                    </fieldset>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>


