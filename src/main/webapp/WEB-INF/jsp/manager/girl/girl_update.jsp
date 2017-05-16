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
<style type="text/css">
#imgShow{cursor: pointer;}
#imgdiv{width: 100px;float: left;margin-right: 50px;}
.checkI{color: green;}
</style>
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
	  	<input type="hidden" name="mainImg" id="mainImg" >
	    <input type="hidden" name="girlImgs" id="girlImgs">
	    <input type="hidden" name="girlId" id="girlId">
	    <div class="box-body">
	      <div class="form-group">
	        <label for="up_img" class="col-sm-2 control-label">主页图片</label>
	
	        <div class="col-sm-10">
	        	<div id="imgdiv"><img id="imgShow" width="100" height="100" /></div>
    			<input type="file" name="file" id="up_img" style="display: none" />
    			<button class="btn btn-primary" type="button" id="uploadMainImg">上传</button>
    			<i class="fa fa-check checkI"></i>
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="girlName" class="col-sm-2 control-label">妹纸名称</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="girlName" name="girlName" placeholder="请输入妹纸名称">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="age" class="col-sm-2 control-label">年龄</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="hight" class="col-sm-2 control-label">身高</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="hight" name="hight" placeholder="请输入身高">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="weight" class="col-sm-2 control-label">体重</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="weight" name="weight" placeholder="请输入体重">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="qq" class="col-sm-2 control-label">QQ</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="qq" name="qq" placeholder="请输入QQ">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="weixin" class="col-sm-2 control-label">微信</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="weixin" name="weixin" placeholder="请输入微信">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="phone" class="col-sm-2 control-label">电话</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入电话">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="price" class="col-sm-2 control-label">价格</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="price" name="price" placeholder="请输入价格">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="address" class="col-sm-2 control-label">地址</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="address" name="address" placeholder="请输入地址">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="title" class="col-sm-2 control-label">标题</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="title" name="title" placeholder="请输入标题">
	        </div>
	      </div>
	      <div class="form-group">
	        <label for="description" class="col-sm-2 control-label">描述</label>
	
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="description" name="description" placeholder="请输入描述">
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
<script type="text/javascript" src="${ctx}/resource/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/girl/girl_update.js"></script>
</body>
</html>