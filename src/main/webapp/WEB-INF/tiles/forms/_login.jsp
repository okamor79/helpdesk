<%--
  Created by IntelliJ IDEA.
  User: OKaminskyi
  Date: 13.09.2019
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@include file="/WEB-INF/taglibs.jsp"%>

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
