<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../../resource/resource.jsp"></jsp:include>
<title>妹纸列表</title>
</head>
<body>
<input type="hidden" id="path" value="${ctx}" />
	<div class="row" id="main">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">妹纸列表</h3>

              <div class="box-tools">
                <div class="input-group input-group-sm" style="width: 150px;">
                  <div class="input-group-btn">
                    <a type="button" class="btn btn-default" id="add" href="${ctx}/girl/goUpdate"><i class="fa fa-plus"></i> 添加妹纸</a>
                    <a type="button" class="btn btn-default" id="search" href="javascript:;"><i class="fa fa-search"></i> 搜索妹纸</a>
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
                  <th>妹纸名称</th>
                  <th>电话</th>
                  <th>价格</th>
                  <th>地址</th>
                  <th>排序</th>
                  <th>操作</th>
                </tr>
                <c:forEach var="girl" items="${girlList}" varStatus="vs">
                	<tr>
	                  <td>${vs.index+1}</td>
	                  <td>
	                  	<img width="75" alt="" src="${ctx}/${girl.mainImg}">
	                  </td>
	                  <td>${girl.girlName}</td>
	                  <td>${girl.phone}</td>
	                  <td>${girl.price}</td>
	                  <td>${girl.address}</td>
	                  <td>${girl.sort}</td>
	                  <td>
	                  	<a href="${ctx}/girl/goUpdate?girlId=${girl.girlId}">编辑</a>
	                  	<input type="hidden" value="${girl.girlId}" />
	                  	<c:if test="${girl.delflag == 0}">
	                  		<a href="javascript:;" class="delete">删除</a>
	                  	</c:if>
	                  	<c:if test="${girl.delflag == 1}">
	                  		<a href="javascript:;" class="delete">复活</a>
	                  	</c:if>
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
      <script type="text/javascript" src="${ctx}/resource/js/girl/girl_list.js"></script>
</body>
</html>