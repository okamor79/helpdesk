<%--
  Created by IntelliJ IDEA.
  User: OKaminskyi
  Date: 25.09.2019
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<legend>
    Користувач: <strong>${user.fullName} (${user.login})</strong>
</legend>

<form:form cssStyle="padding: 35px" action="/settings/users/edit" method="post" modelAttribute="user">
    <div class="form-group row">
        <label for="fullName" class="col-sm-2 col-form-label">П.І.Б. користувача</label>
        <div class="col-sm-6">
            <form:input path="fullName" class="form-control input-md"></form:input>
        </div>
    </div>
    <div class="form-group row">
        <label for="department" class="col-sm-2 col-form-label">Місце праці</label>
        <div class="col-sm-6">
            <form:input path="department" class="form-control input-md"></form:input>
        </div>
    </div>
    <div class="form-group row">
        <label for="position" class="col-sm-2 col-form-label">Посада користувача</label>
        <div class="col-sm-6">
            <form:input path="position" class="form-control input-md"></form:input>
        </div>
    </div>

    <div class="form-group row">
        <label for="role" class="col-sm-2 col-form-label">Роль доступу</label>
        <div class="col-sm-6">
            <form:select path="role" class="form-control select-md">
                <form:option value="ROLE_ADMIN">Адміністратор</form:option>
                <form:option value="ROLE_SUPERVISOR">Супервайзер</form:option>
                <form:option value="ROLE_AGENT">Агент</form:option>
                <form:option value="ROLE_AUDITOR">Аудитор</form:option>
                <form:option value="ROLE_USER">Користувач</form:option>
            </form:select>
        </div>
    </div>
    <div class="form-group row">
        <label for="phone" class="col-sm-2 col-form-label">Контактний телефон</label>
        <div class="col-sm-6">
            <form:input path="phone" class="form-control input-md"></form:input>
        </div>
    </div>
    <div class="form-group row">
        <label for="mail" class="col-sm-2 col-form-label">Поштова скринька</label>
        <div class="col-sm-6">
            <form:input path="mail" class="form-control input-md" readonly="true"></form:input>
        </div>
    </div>
    <form:hidden path="login"/>
    <form:hidden path="id"/>
    <button type="submit" class="btn btn-primary">Зберегти</button>
    <button type="button" class="btn btn-secondary" onclick="history.go(-1)">Закрити</button>
</form:form>