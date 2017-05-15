<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../../resource/resource.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/js/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resource/js/diyUpload/css/diyUpload.css">
<title>妹纸管理</title>
</head>
<body>
<input type="hidden" id="path" value="${ctx}" />

<div class="col-md-7 col-md-offset-2">
	<div class="box box-info">
	  <div class="box-header with-border">
	    <h3 class="box-title">妹纸</h3>
	  </div>
	  <!-- /.box-header -->
	  <!-- form start -->
	  <form class="form-horizontal" id="updateForm">
	    <div class="box-body">
	      <div class="form-group">
	        <label for="mainImg" class="col-sm-2 control-label">主页图片</label>
	
	        <div class="col-sm-10">
	        	<div id="imgdiv"><img id="imgShow" width="100" height="100" /></div>
    			<input type="file" id="up_img" />
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="sort" class="col-sm-2 control-label">排序</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="sort" name="sort" placeholder="请输入排序">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="sort" class="col-sm-2 control-label">选择图片</label>
	
	       <div id="uploadImg"></div>
	      </div>
	    </div>
	    <!-- /.box-body -->
	    <div class="box-footer">
	      <button type="reset" class="btn btn-default">取消</button>
	      <button type="submit" class="btn btn-info pull-right" id="subBtn">提交</button>
	    </div>
	    <!-- /.box-footer -->
	  </form>
	</div>
</div>
      
<script type="text/javascript" src="${ctx}/resource/js/diyUpload/js/webuploader.html5only.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/jquery-validate/jquery.validate-1.13.1.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/uploadPreview/uploadPreview.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/girl/girl_update.js"></script>
</body>
</html>