<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@include file="/WEB-INF/taglibs.jsp" %>

<legend>
    Адімністрування проектів
</legend>
<div class="row" style="padding: 25px">
    <div class="col-sm-2">
        <a href="projects/create" class="btn btn-sm btn-warning ">Створити
            проект</a>
    </div>
</div>
<div class="row">
    <table class="table table-sm table-striped" style="font-size: 11px; cursor: pointer">
        <thead>
        <tr>
            <th class="text-md-center">Код проекту</th>
            <th class="text-md-center">Назва проекту</th>
            <th class="text-md-center">Опис</th>
            <th class="text-md-center">Агенти</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projectList}" var="prjList">
            <tr onclick="document.location.href='/settings/projects/modify/${prjList.id}'">
                <td class="text-md-center">${prjList.code}</td>
                <td>${prjList.title}</td>
                <td>${prjList.description}</td>
                <td>
                    <c:forEach items="${prjList.agent}" var="agent">
                        ${agent.fullName}<br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

