<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@include file="/WEB-INF/taglibs.jsp" %>

<legend>
    ${pageTitle}
</legend>

<div class="row" style="padding: 25px">
    <form:form action="/settings/projects/create" method="post" modelAttribute="createProject">
        <div class="form-group row">
            <label for="code">Символьний код проекту</label>
            <form:input path="code" cssClass="form-control"></form:input>
            <form:errors path="code" cssStyle="font-size: 9px; color:red;"></form:errors>
        </div>
        <div class="form-group row">
            <form:label path="title">Назва проекту</form:label>
            <form:input path="title" cssClass="form-control input-group-sm"></form:input>
        </div>
        <div class="form-group row">
            <label for="description">Опис проекту</label>
            <form:textarea path="description" cssClass="form-control text-area"></form:textarea>
        </div>
        <div class="form-group row">
            <label for="agent">Агенти</label>
            <form:select path="agent" cssClass="form-control" multiple="true">
                <form:options items="${agents}" itemValue="id" itemLabel="fullName"/>
            </form:select>
            <span  class="badge badge-light">Для вибору кількох агентів утримуйте натиснутою клавішу CTRL</span>
        </div>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/settings/projects'">Закрити
        </button>
        <button type="submit" class="btn btn-primary">Створити</button>
    </form:form>

</div>
