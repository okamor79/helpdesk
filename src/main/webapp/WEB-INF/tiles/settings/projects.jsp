<%--
  Created by IntelliJ IDEA.
  User: OKaminskyi
  Date: 17.09.2019
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@include file="/WEB-INF/taglibs.jsp" %>

<legend>
    Адімністрування проектів
</legend>
<div class="row" style="padding-top: 25px">
    <div class="col-sm-2">

        <a href="" class="btn btn-sm btn-warning " data-toggle="modal" data-target="#modalFormCreateProject">Створити
            проект</a>

    </div>
</div>

<div class="modal fade" id="modalFormCreateProject" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Новий проект</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form:form action="" method="post" cssStyle="padding: 15px">
                    <div class="form-group row">
                        <label for="prjCode">Символьний код проекту</label>
                        <input type="text" name="prjCode" class="form-control" id="prjCode" placeholder=""/>
                    </div>
                    <div class="form-group row">
                        <label for="prjName">Назва проекту</label>
                        <input type="text" name="prjName" class="form-control" id="prjName" placeholder=""/>
                    </div>
                    <div class="form-group row">
                        <label for="prjDesc">Опис проекту</label>
                        <textarea class="form-control" id="prjDesc"></textarea>
                    </div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрити</button>
                    <button type="submit" class="btn btn-primary">Створити</button>
                </form:form>
            </div>
        </div>
    </div>
</div>