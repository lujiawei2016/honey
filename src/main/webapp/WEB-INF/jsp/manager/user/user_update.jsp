<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../../resource/resource.jsp"></jsp:include>
<title><c:if test="${empty user}">新增</c:if><c:if test="${!empty user}">编辑</c:if>用户</title>
<link type="text/css" rel="stylesheet" href="${ctx}/resource/js/zTree_v3/zTreeStyle/zTreeStyle.css" />
<style type="text/css">
#treeDiv{height: 200px;position: absolute;background: #FFF;overflow: auto;border: 1px solid #EEE;}
</style>
</head>
<body>
<input type="hidden" value="${ctx}" id="path" />
<input type="hidden" value="${user.id}" id="userId" />
<input type="hidden" value="" id="treeId" />
<div class="col-md-7 col-md-offset-2">
	<div class="box box-info">
	  <div class="box-header with-border">
	    <h3 class="box-title"><c:if test="${empty user}">新增</c:if><c:if test="${!empty user}">编辑</c:if>用户</h3>
	  </div>
	  <!-- /.box-header -->
	  <!-- form start -->
	  <form class="form-horizontal">
	    <div class="box-body">
	      <div class="form-group">
	        <label for="username" class="col-sm-2 control-label">用户名</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="username" placeholder="请输入用户名" value="${user.username}">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="treeTextArea" class="col-sm-2 control-label">拥有角色</label>
	
	        <div class="col-sm-10">
	          <textarea id="treeTextArea" class="form-control" rows="5" cols="" style="resize:none;" readonly="readonly"></textarea>
	          <div class="col-sm-11 ztree" id="treeDiv"></div>
	        </div>
	      </div>
	    </div>
	    <!-- /.box-body -->
	    <div class="box-footer">
	      <button type="reset" class="btn btn-default">取消</button>
	      <button type="button" class="btn btn-info pull-right" id="subBtn">提交</button>
	    </div>
	    <!-- /.box-footer -->
	  </form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/resource/js/zTree_v3/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/zTree_v3/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/user/user_update.js"></script>
</body>
</html>