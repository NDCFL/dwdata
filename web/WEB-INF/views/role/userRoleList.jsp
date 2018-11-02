<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/19
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户角色列表</title>
    <jsp:include page="../common/bootstraptablecss.jsp"></jsp:include>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>用户角色列表</h5>
        </div>
        <div class="ibox-content">
            <div class="panel panel-default">
                <div class="panel-heading">
                    查询条件
                </div>
                <div class="panel-body form-group" style="margin-bottom:0px;">
                    <table id="mytab" name="mytab" class="table table-hover"></table>
                    <div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
                        <button id="btn_delete" onclick="deleteMany();" type="button" class="btn btn-default" style="display: block;">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true" ></span>批量修改状态
                        </button>
                        <button id="btn_add" type="button" class="btn btn-default" data-toggle="modal" data-target="#webAdd">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--网站数据的新增--%>
<!-- 模态框（Modal） -->
<div class="modal fade" id="webAdd" tabindex="-1" role="dialog" aria-labelledby="webAddLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="webAddTitle">
                    新增用户角色
                </h4>
            </div>
            <form class="form-horizontal" method="post" id="formadd">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户昵称：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="user_id" required name="userId">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所属角色：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="role_id" required name="roleId">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="add" class="btn btn-primary" data-dismiss="modal">
                        确认新增
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<input type="hidden" value=""  id="deleteId"/>
<div class="modal fade" id="updateStatus" tabindex="-1" role="dialog" aria-labelledby="webAddLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" >
                    批量修改状态
                </h4>
            </div>
            <form class="form-horizontal" method="post" id="update_status">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">状态：</label>
                        <div class="col-sm-8">
                            <select class="form-control"  id="status" required name="status">
                                <option value="0">启用</option>
                                <option value="1">停用</option>
                            </select>
                        </div>
                        <input id="statusId" type="hidden" name="manyId" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="updateSta" class="btn btn-primary" data-dismiss="modal">
                        确认修改
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%--网站新增结束--%>
<%--网站信息的修改--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    用户角色的修改
                </h4>
            </div>
            <form class="form-horizontal" id="updateform" >
                <div class="modal-body">

                    <input type="hidden" name="id" id="id" value="">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户昵称：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="userId" required name="userId">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所属角色：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="roleId" required name="roleId">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="update" class="btn btn-primary" data-dismiss="modal">
                        确认修改
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%--网站信息的修改--%>
<jsp:include page="../common/bootstraptablejs.jsp"></jsp:include>
<script src="<%=path%>/static/js/pageJs/userRole.js"></script>
<script src="<%=path%>/static/js/select2.min.js"></script>


</body>
<%--<script>--%>
<%--$(function () {--%>
<%--alert("asdflsfa");--%>
<%--layer.msg('已发布', {icon:1,time:1000});--%>
<%--layer.msg('已发布', {icon:2,time:1000});--%>
<%--layer.msg('已发布', {icon:3,time:1000});--%>
<%--layer.msg('已发布', {icon:4,time:1000});--%>
<%--layer.msg('已发布', {icon:5,time:1000});--%>
<%--layer.msg('已发布', {icon:6,time:1000});--%>
<%--layer.msg('已发布', {icon:7,time:1000});--%>
<%----%>
<%--});--%>

<%--</script>--%>
<script>
    $(function(){
        $.post(
            "/user/getUserIdAndName",
            function(data){
                $("#userId").select2({
                    data: data,
                    placeholder:'请选择用户',
                    allowClear:false
                })
                $("#select2-userId-container").remove();
                $("#user_id").select2({
                    data: data,
                    placeholder:'请选择用户',
                    allowClear:false
                })
                $("#select2-user_id-container").remove();
            },
            "json"

        );
        $.post(
            "/role/getRoleIdAndTitle",
            function(data){
                $("#roleId").select2({
                    data: data,
                    placeholder:'请选择角色',
                    allowClear:false
                })
                $("#role_id").select2({
                    data: data,
                    placeholder:'请选择角色',
                    allowClear:false
                })
                $("#select2-roleId-container").remove();
                $("#select2-role_id-container").remove();
            },
            "json"
        );
    });
</script>
</html>
