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

<!DOCTYPE html>
<html lang="ua">
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" type="text/css">

</head>
<body>
<nav class="navbar navbar-expand-xl navbar-dark bg-primary">
    <a class="navbar-brand " href="/"><spring:message code="company.name"/> Helpdesk</a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
                <span style="color: white">${userName}</span>
            </li>
        </ul>
    </div>
</nav>

<div class="">
    ${userAttr}
</div>

</body>
</html>
