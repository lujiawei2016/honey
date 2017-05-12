<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../../resource/resource.jsp"></jsp:include>
<title>列出所有用户</title>
</head>
<body>
<input type="hidden" value="${ctx}" id="path" />
	<div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">用户管理</h3>

              <div class="box-tools">
                <div class="input-group input-group-sm" style="width: 150px;">
                  <div class="input-group-btn">
                    <a href="${ctx}/user/goUpdate" type="button" class="btn btn-default" id="add"><i class="fa fa-plus"></i> 添加用户</a>
                  </div>
                </div>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody><tr>
                  <th>序号</th>
                  <th>用户名</th>
                  <th>操作</th>
                </tr>
                <c:forEach var="user" items="${obj.lists}" varStatus="status">
                	<tr>
	                  <td>${status.index+1}</td>
	                  <td>${user.username}</td>
	                  <td>
	                  	<a href="${ctx}/user/goUpdate?userId=${user.id}">编辑</a>
	                  	<input type="hidden" value="${user.id}">
	                  	<a href="javascript:;" class="delete">删除</a>
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
      
      <!-- 分页 -->
      <div style="text-align: center;">
      	<ul id="pageLimit"></ul>
      </div>
      
</body>
<script type="text/javascript" src="${ctx}/resource/js/paginator/bootstrap-paginator.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/user/user_list.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	if('${obj.countPage}' != '0'){
		$('#pageLimit').bootstrapPaginator({
		    currentPage: "${currentPage}",
		    totalPages: "${obj.countPage}",
		    size:"normal",
		    bootstrapMajorVersion: 3,
		    alignment:"right",
		    numberOfPages:5,
		    itemTexts: function (type, page, current) {
		        switch (type) {
			        case "first": return "首页";
			        case "prev": return "上一页";
			        case "next": return "下一页";
			        case "last": return "末页";
			        case "page": return page;
		        }
		    },
		    onPageClicked:function(event, originalEvent, type,page){
		    	window.location.href='${ctx}/user/listUser?pageNum='+page+'';
		    }
		});
	}
});
</script>
</html>