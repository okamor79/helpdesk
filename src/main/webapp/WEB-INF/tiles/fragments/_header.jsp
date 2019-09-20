<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-expand-xl navbar-dark bg-primary">
    <a class="navbar-brand " href="/"><spring:message code="company.name"/> Helpdesk</a>

    <div class="collapse navbar-collapse">
        <sec:authorize access="isAuthenticated()">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a href="/settings/info" class="nav-link">Налаштування</a>
                </li>
            </ul>
            <a href="/" class="nav-item" style="text-decoration: none">
                <span class="btn-block btn" data-toggle="tooltip" data-html="true"
                      title="${userDepartament}
${userPosition}
${userPhone}
${userMail}">${userName}</span></a>
        </sec:authorize>
    </div>

</nav>
