<%--
  Created by IntelliJ IDEA.
  User: OKaminskyi
  Date: 12.09.2019
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ua">
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" type="text/css">

    <style>
        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .login-form {
            max-width: 400px;
            margin: 0 auto;
            margin-top: 100px;
            border: 1px solid gray;
        }

    </style>

</head>
<body>
<nav class="navbar navbar-expand-xl navbar-dark bg-primary">
    <a class="navbar-brand " href="/"><spring:message code="company.name"/> Helpdesk</a>
    <div class="collapse navbar-collapse">
    </div>
</nav>

<div class="container">

    <div class="login-form">
        <form:form action="/login" method="post" cssClass="form-signin">
            <div class="form-group row">
                <label for="username">Логін:</label>
                <input type="text" name="username" class="form-control" id="username" placeholder="Логін"/>
            </div>
            <div class="form-group row">
                <label for="password">Пароль:</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="Пароль"/>
            </div>
            <button type="submit" class="btn btn-success btn-length">Ввійти</button>
        </form:form>
    </div>
</div>

</body>
</html>
