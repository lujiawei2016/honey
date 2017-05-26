<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <jsp:include page="../../../../resource/resource.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/js/tree/css/tree.css" />
    <title>微信菜单管理</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
  <input type="hidden" id="path" value="${ctx}" />
  
  <section class="content">
  	<div class="row">
      	<div class="col-md-4">
	       	<div class="tree well">
			    <ul>
			        <li>
			            <span> 微信菜单列表</span> <img alt="微信菜单生成" src="${ctx}/resource/images/refresh.png" class="createWxMenu"> <img alt="正在生成微信菜单" src="${ctx}/resource/images/loading.gif" class="loadWxMenu">
			            <ul>
				            <c:forEach items="${wxFahterMenuList}" var="fatherMenu">
				            	<li>
				                	<span title="${fatherMenu.name}" class="fatherMenuSpan"> ${fatherMenu.name}</span> <input type="hidden" value="${fatherMenu.id}" /><a href="javascript:;" class="deleteA">删除</a>
				                    <ul>
				                    	<c:forEach items="${WxChildrenMenuList}" var="childrenMenu">
				                    		<c:if test="${fatherMenu.id == childrenMenu.fatherId}">
					                    		<li>
							                        <span class="childrenMenuSpan" title="${childrenMenu.name}"> ${childrenMenu.name}</span> <input type="hidden" value="${childrenMenu.id}" /><a href="javascript:;" class="deleteA">删除</a>
						                        </li>
					                        </c:if>
				                    	</c:forEach>
				                    </ul>
				                </li>
				            </c:forEach>
			            </ul>
			        </li>
			    </ul>
			</div>
		</div>
		<div class="col-md-5">
			<!-- form start -->
	              <form class="form-horizontal" action="" method="post" id="wxMenuForm">
	              	<div class="box-body">
	                   <div class="form-group">
	                   	<label for="level" class="col-sm-4 control-label" style="padding-top: 0">菜单级别</label>
	                   	<div class="col-sm-8">
	                   		<select id="level" class="selectStyle level form-control" name="level">
	                   			<option value="" selected="selected">---请选择---</option>
	                   			<option value="1">&nbsp;一级菜单&nbsp;</option>
	                   			<option value="2">&nbsp;二级菜单&nbsp;</option>
	                   		</select>
	                    </div>
	                   </div>
	                   <div class="form-group">
	                   	<label for="erji" class="col-sm-4 control-label" style="padding-top: 0">是否有二级菜单</label>
	                   	<div class="col-sm-8">
	                   		<select id="erji" class="selectStyle erji form-control" name="erji">
	                   			<option value="" selected="selected">---请选择---</option>
	                   			<option value="1">&nbsp;有&nbsp;</option>
	                   			<option value="0">&nbsp;无&nbsp;</option>
	                   		</select>
	                    </div>
	                   </div>
	                   <div class="form-group">
	                   	<label for="fatherId" class="col-sm-4 control-label" style="padding-top: 0">上级菜单</label>
	                   	<div class="col-sm-8">
	                   		<select id="fatherId" class="selectStyle fatherId form-control" name="fatherId">
	                   			<c:forEach var="fahterMenu" items="${wxFahterMenuList}">
	                   				<option value="${fahterMenu.id}">${fahterMenu.name}</option>
	                   			</c:forEach>
	                   		</select>
	                    </div>
	                   </div>
	                   <div class="form-group">
	                   	<label for="type" class="col-sm-4 control-label" style="padding-top: 0">菜单类型</label>
	                   	<div class="col-sm-8">
	                   		<select id="type" class="selectStyle type form-control" name="type">
	                   			<option value="" selected="selected">---请选择---</option>
	                   			<option value="click">&nbsp;click&nbsp;</option>
	                   			<option value="view">&nbsp;view&nbsp;</option>
	                   		</select>
	                    </div>
	                   </div>
	                   <div class="form-group">
	                   	<label for="name" class="col-sm-4 control-label">菜单名称</label>
	                   	<div class="col-sm-8">
	                   		<input type="text" class="form-control" id="name" name="name" placeholder="请输入菜单名称">
	                   	</div>
	                   </div>
	                   <div class="form-group">
	                   	<label for="url" class="col-sm-4 control-label">跳转地址</label>
	                   	<div class="col-sm-8">
	                   		<input type="text" class="form-control" id="url" name="url" placeholder="请输入跳转地址">
	                   	</div>
	                   </div>
	                   <div class="form-group">
	                   	<label for="menuKey" class="col-sm-4 control-label">回复关键字</label>
	                   	<div class="col-sm-8">
	                   		<input type="text" class="form-control" id="menuKey" name="menuKey" placeholder="请输入菜单标志">
	                   	</div>
	                   </div>
	                   <div class="form-group">
	                   	<label for="sort" class="col-sm-4 control-label">菜单排序</label>
	                   	<div class="col-sm-8">
	                   		<input type="text" class="form-control" id="sort" name="sort" placeholder="请输入序号">
	                   	</div>
	                   </div>
	                   <div class="form-group">
	                   	<label class="col-sm-3 control-label"></label>
	                   	<label for="sort" class="col-sm-2 control-label">
	                   		<button type="submit" class="btn btn-info">提交</button>
	                   	</label>
	                   	<label class="col-sm-2 control-label">
	                   		<button type="reset" class="btn btn-default">重置</button>
	                   	</label>
	                   </div>
	                </div><!-- /.box-body -->
	              </form>
		</div>
	</div>
  </section>
  
  <script type="text/javascript" src="${ctx}/resource/js/jquery-validate/jquery.validate-1.13.1.js"></script>
  <script type="text/javascript" src="${ctx}/resource/js/tree/js/tree.js"></script>
  <script type="text/javascript" src="${ctx}/resource/js/wxMenu/wxMenu.js"></script>
    
  </body>
</html>
