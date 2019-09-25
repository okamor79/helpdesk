<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/webjars/bootstrap-glyphicons/bdd2cbfba0/css/bootstrap-glyphicons.css">


    <c:if test="${pageTitle != null}" >
        <tiles:putAttribute name="title" value="${pageTitle}" />
    </c:if>

    <title><tiles:getAsString name="title" /></title>


</head>
<body>
<tiles:insertAttribute name="header"/>

<div class="container-fluid text-left ">
    <div class="row">
        <div class="col-sm-2"><br>
            <tiles:insertAttribute name="sidebar"/>
        </div>
        <div class="col-sm-10" style="padding: 15px">
            <tiles:insertAttribute name="content"/>
        </div>
    </div>
</div>
<tiles:insertAttribute name="footer"/>
</body>
</html>







<%--<div class="container-fluid" style="padding: 15px">--%>
<%--    <h4>Налаштування</h4>--%>
<%--    <div class="container-fluid" style="padding: 20px">--%>
<%--        <div class="row">--%>
<%--            <div class="col-2">--%>
<%--                <ul class="navbar-nav">--%>
<%--                    <li class="nav-item"><a href="settings/users" class="nav-link">Користувачі</a></li>--%>
<%--                    <li class="nav-item"><a href="" class="nav-link">Проекти</a></li>--%>
<%--                    <li class="nav-item"><a href="" class="nav-link">Призначення агентів</a></li>--%>
<%--                </ul>--%>

<%--                                <tiles:insertAttribute name="sidebar" />--%>
<%--            </div>--%>
<%--            <div class="col-sm">--%>
<%--                <div class="row">--%>
<%--                    <div class="col-sm-2">--%>
<%--                    <a href="synchronize">--%>
<%--                        <input type="submit" class="btn btn-danger btn-sm text-right" value="Синхронізація з AD"/>--%>
<%--                    </a></div>--%>
<%--                    <div class="col-sm-4">--%>
<%--                    <input type="search" class="form-control form-control-sm">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <br/>--%>
<%--                <table class="table table-sm">--%>
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th scope="col" class="text-md-center">Логін</th>--%>
<%--                        <th scope="col" class="text-md-center border-left border-right">ПІБ</th>--%>
<%--                        <th scope="col" class="border-right text-md-center">Поштова скринька</th>--%>

<%--                        <th scope="col" class="border-right text-md-center">Посада</th>--%>
<%--                        <th scope="col" class="border-right text-md-center">Департамент</th>--%>
<%--                        <th scope="col" class="border-right text-md-center">Телефон</th>--%>
<%--                        <th scope="col" class="border-right text-md-center">Роль</th>--%>
<%--                        <th scope="col"></th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    <c:forEach items="${userList}" var="user">--%>
<%--                        <tr>--%>
<%--                            <td>${user.login}</td>--%>
<%--                            <td>${user.fullName}</td>--%>
<%--                            <td>${user.mail}</td>--%>
<%--                            <td>${user.position}</td>--%>
<%--                            <td>${user.department}</td>--%>
<%--                            <td>${user.phone}</td>--%>
<%--                            <td class="text-md-center">${user.role}</td>--%>
<%--                            <td class="text-md-center">--%>
<%--                                <a style="text-decoration: none" href="/settings/user/edit/${user.id}"><span class="btn btn-sm btn-success glyphicon glyphicon-pencil" style="color:white"></span></a>--%>
<%--                                <a style="text-decoration: none" href="/settings/user/remove/${user.id}"><span class="btn btn-sm btn-danger glyphicon glyphicon-remove" style="color:white"></span></a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
<%--                    </tbody>--%>

<%--                </table>--%>


<%--            </div>--%>

<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>