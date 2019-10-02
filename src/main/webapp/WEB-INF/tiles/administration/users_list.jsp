<%--
  Created by IntelliJ IDEA.
  User: OKaminskyi
  Date: 17.09.2019
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>

<c:url var="firstUrl" value="/administration/users?page=0&search=${searchUser}"/>
<c:url var="prevUrl" value="/administration/users?page=${currentIndex - 1}&search=${searchUser}"/>
<c:url var="nextUrl" value="/administration/users?page=${currentIndex + 1}&search=${searchUser}"/>
<c:url var="lastUrl" value="/administration/users?page=${usersList.totalPages - 1}&search=${searchUser}"/>

<div class="row">
    <h5>Адімністрування користувачів</h5>

</div>
<div class="row" style="padding-top: 25px">


    <div class="col-sm-2">
        <a href="synchronize">
            <input type="submit" class="btn btn-danger btn-sm text-right" value="Синхронізація з AD"/>
        </a>
    </div>
    <div class="col-sm-4">
        <form:form method="post" action="/administration/user/search" modelAttribute="searchUser">
            <div class="input-group input-group-sm">
                <form:input path="search" cssClass="form-control"/>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="submit">Шукати</button>
                </div>
            </div>
        </form:form>

    </div>
</div>
<div class="row">&nbsp;</div>
<div class="row" style="padding: 5px 10px">
    <table class="table table-sm table-hover table-condensed table-bordered" style="font-size: 0.8em">
        <thead>
        <tr>
            <th scope="col" class="text-sm-center">Логін</th>
            <th scope="col" class="text-sm-center border-left border-right">ПІБ</th>
            <th scope="col" class="border-right text-md-center">Поштова скринька</th>
            <th scope="col" class="border-right text-md-center">Посада</th>
            <th scope="col" class="border-right text-md-center">Департамент</th>
            <th scope="col" class="border-right text-md-center">Телефон</th>
            <th scope="col" class="border-right text-md-center">Роль</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersPageListByPageSize}" var="user">
            <tr>
                <td>${user.login}</td>
                <td>${user.fullName}</td>
                <td>${user.mail}</td>
                <td>${user.position}</td>
                <td>${user.department}</td>
                <td class="text-md-center">${user.phone}</td>
                <td class="text-md-center">${user.role}</td>
                <td class="text-md-center">
                    <small><a style="text-decoration: none; color: green"
                              href="/administration/user/edit/${user.id}"
                              class="glyphicon  glyphicon-pencil"></a>&nbsp;&nbsp;
                        <a style="text-decoration: none; color: red"
                           href="/administration/user/remove/${user.id}"
                           class="glyphicon glyphicon-remove"></a></small>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
<div class="container">
    <nav aria-label="User navigate">
        <ul class="pagination pagination-sm">
            <c:choose>
                <c:when test="${currentIndex == 0}">
                    <li class="disabled page-item"><a href="#" class="page-link"><span
                            class="glyphicon glyphicon-fast-backward"></span></a></li>
                    <li class="disabled page-item"><a href="#" class="page-link"><span
                            class="glyphicon glyphicon-backward"></span></a></li>
                    <li class="active page-item"><a href="${firstUrl}" class="page-link">1</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a href="${firstUrl}" class="page-link"><span
                            class="glyphicon glyphicon-fast-backward"></span></a></li>
                    <li class="page-item"><a href="${prevUrl}" class="page-link"><span
                            class="glyphicon glyphicon-backward"></span></a></li>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                <c:url var="pageUrl"
                       value="/administration/users?page=${i}&search=${searchUser}"/>
                <c:choose>
                    <c:when test="${i == currentIndex}">
                        <li class="page-item active"><a href="#" class="page-link">${i+1}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a href="${pageUrl}" class="page-link">${i+1}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentIndex + 1 == usersList.totalPages}">
                    <li class="disabled page-item"><a href="#" class="page-link"><span
                            class="glyphicon glyphicon-forward"></span></a></li>
                    <li class="disabled page-item"><a href="#" class="page-link"><span
                            class="glyphicon glyphicon-fast-forward"></span></a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a href="${nextUrl}" class="page-link"><span
                            class="glyphicon glyphicon-forward"></span></a></li>
                    <li class="page-item"><a href="${lastUrl}" class="page-link"><span
                            class="glyphicon glyphicon-fast-forward"></span></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</div>

