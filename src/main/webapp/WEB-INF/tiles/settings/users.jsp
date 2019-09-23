<%--
  Created by IntelliJ IDEA.
  User: OKaminskyi
  Date: 17.09.2019
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>

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
        <input type="search" class="form-control form-control-sm">
    </div>
</div>
<div class="row"style="padding: 20px 10px">
    <table class="table table-sm">
        <thead>
            <tr>
                <th scope="col" class="text-md-center">Логін</th>
                <th scope="col" class="text-md-center border-left border-right">ПІБ</th>
                <th scope="col" class="border-right text-md-center">Поштова скринька</th>
                <th scope="col" class="border-right text-md-center">Посада</th>
                <th scope="col" class="border-right text-md-center">Департамент</th>
                <th scope="col" class="border-right text-md-center">Телефон</th>
                <th scope="col" class="border-right text-md-center">Роль</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
                            <c:forEach items="${userList}" var="user">
                                <tr>
                                    <td>${user.login}</td>
                                    <td>${user.fullName}</td>
                                    <td>${user.mail}</td>
                                    <td>${user.position}</td>
                                    <td>${user.department}</td>
                                    <td>${user.phone}</td>
                                    <td class="text-md-center">${user.role}</td>
                                    <td class="text-md-center">
                                        <a style="text-decoration: none" href="/settings/user/edit/${user.id}"><span class="btn btn-sm btn-success glyphicon glyphicon-pencil" style="color:white"></span></a>
                                        <a style="text-decoration: none" href="/settings/user/remove/${user.id}"><span class="btn btn-sm btn-danger glyphicon glyphicon-remove" style="color:white"></span></a>
                                    </td>
                                </tr>
                            </c:forEach>
        </tbody>

    </table>
</div>



<%--                    </tbody>--%>

<%--                </table>--%>


<%--            </div>--%>


