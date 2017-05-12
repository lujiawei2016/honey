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
<title>banner列表</title>
</head>
<body>
<input type="hidden" id="path" value="${ctx}" />
	<div class="row" id="main">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">banner列表</h3>

              <div class="box-tools">
                <div class="input-group input-group-sm" style="width: 150px;">
                  <div class="input-group-btn">
                    <a type="button" class="btn btn-default" id="add" href="${ctx}/banner/goUpdate"><i class="fa fa-plus"></i> 添加banner</a>
                  </div>
                </div>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody>
                <tr>
                  <th>ID</th>
                  <th>图片</th>
                  <th>跳转地址</th>
                  <th>排序</th>
                  <th>操作</th>
                </tr>
                <c:forEach var="banner" items="${bannerList}" varStatus="vs">
                	<tr>
	                  <td>${vs.index+1}</td>
	                  <td>
	                  	<img alt="${banner.imgName}" src="${ctx}/${banner.imgUrl}" width="150" height="72">
	                  </td>
	                  <td>${banner.url}</td>
	                  <td>${banner.sort}</td>
	                  <td>
	                  	<a href="javascript:;" class="delete">删除</a>
	                  	<input type="hidden" value="${banner.bannerId}" />
	                  </td>
	                </tr>
                </c:forEach>
              </tbody></table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
      </div>
      
<script type="text/javascript" src="${ctx}/resource/js/diyUpload/js/webuploader.html5only.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/banner/banner_list.js"></script>
</body>
</html>