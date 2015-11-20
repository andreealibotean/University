<%--
  Created by IntelliJ IDEA.
  User: andreealibotean
  Date: 11/19/2015
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add professors for course</title>
    <link href="<c:url value="/resources/core/css/bootstrap.min.css" />" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="navbar-header">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
            <li><a href="<%=request.getContextPath()%>/students">Students</a></li>
            <li><a href="<%=request.getContextPath()%>/professors">Professors</a></li>
            <li><a href="<%=request.getContextPath()%>/courses">Courses</a></li>
        </ul>
    </div>
    <div class="bs-docs-section">
        <div class="row">
            <div class="col-lg-12">
                <div class="page-header">
                    <h1 id="forms">Course Details</h1>
                </div>
            </div>
        </div>

        <div class="row" style="left: 10px">
            <div class="col-lg-8">
                <div class="well bs-component">
                    <form class="form-horizontal">
                        <fieldset>
                            <legend>Add professors for course:</legend>


                            <div class="form-group">
                                <label class="col-lg-2 control-label">Name:</label>

                                <div class="col-lg-8">
                                    <div class="radio">
                                        <label>
                                            ${courseDTO.name}
                                        </label>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-lg-12">
                                    <a class="btn btn-default"
                                       href="http://localhost:8080/courses">Back to
                                        courses</a>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>

        <%--List of professors for course--%>
        <div class="row table-bordered">
            <div class="form-group">
                <label class="col-lg-2 control-label">Professors teaching at course:</label>

                <div class="col-lg-10">
                    <div class="radio">
                        <label>
                            <table class="table table-striped table-hover ">
                                <tbody>
                                <c:forEach var="professorDTO" items="${registeredProfessors}">
                                    <tr>
                                        <td><a class="btn btn-default"
                                               href="<%=request.getContextPath()%>/professors/${professorDTO.idProfessor}">${professorDTO.name}${' '}${professorDTO.surname}</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </label>
                    </div>
                </div>

            </div>
        </div>


        <%--Form to add professors --%>
        <c:choose>
            <c:when test="${not empty professors}">
                <div class="row table-bordered">
                    <div class="form-group">

                        <form:form method="post" modelAttribute="courseDTO" class="form-horizontal">
                            <fieldset>

                                <label class="col-lg-2 control-label">Enroll professors:</label>

                                <div class="col-lg-10">
                                    <div class="radio">
                                        <label>
                                            <table class="table table-striped table-hover ">
                                                <tbody>
                                                <c:forEach var="professorDTO" items="${professors}">
                                                    <tr>
                                                        <td>
                                                            <form:checkbox path="professorsIds"
                                                                           value="${professorDTO.idProfessor}"/>${professorDTO.name}${' '}${professorDTO.surname}
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                        </label>

                                    </div>
                                </div>



                                <div class="form-group">
                                    <div class="col-lg-6 col-lg-offset-6">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </div>
                            </fieldset>
                        </form:form>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <b>There are no more professors available to enroll to this course.Please return to</b> <a
                    class="btn btn-default" href="<%=request.getContextPath()%>/courses">Courses</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
