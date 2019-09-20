<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@include file="/WEB-INF/taglibs.jsp"%>

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
  <tiles:insertAttribute name="header" />
  <tiles:insertAttribute name="body" />
  <tiles:insertAttribute name="footer" />



<script src="/webjars/jquery/3.4.1/jquery.min.js" />
<script src="/webjars/popper.js/1.15.0/umd/popper.min.js" />
</body>
</html>
