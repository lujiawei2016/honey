<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../../resource/resource.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="${ctx}/resource/js/zTree_v3/zTreeStyle/zTreeStyle.css" />
<style type="text/css">
#treeDiv{height: 200px;position: absolute;background: #FFF;overflow: auto;}
</style>
<title>角色列表</title>
</head>
<body>

<input type="hidden" id="path" value="${ctx}" />
	<div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">角色列表</h3>

              <div class="box-tools">
                <div class="input-group input-group-sm" style="width: 150px;">
                  <div class="input-group-btn">
                    <button type="button" class="btn btn-default" id="add"><i class="fa fa-plus"></i> 添加角色</button>
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
                  <th>角色名称</th>
                  <th>操作</th>
                </tr>
                <c:forEach var="roles" items="${roleList.lists}" varStatus="vs">
	                <tr>
	                  <td>${vs.index+1}</td>
	                  <td class="nameTh">${roles.rolesName}</td>
	                  <td>
	                  	<a href="javascript:;" class="edit">编辑</a><input type="hidden" value="${roles.id}">
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


<!-- 模态框（Modal） -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="modalLabel">
					新增角色
				</h4>
			</div>
			<div class="modal-body">
				<div class="box box-info">
				  <form class="form-horizontal" id="updateFrom">
				  	<input type="hidden" name="id" id="rolesId" />
				  	<input type="hidden" name="treeId" id="treeId" />
				    <div class="box-body">
				      <div class="form-group">
				        <label for="type" class="col-sm-2 control-label">类型</label>
				        <div class="col-sm-10">
				          <select id="type" name="type" class="form-control">
				          	<option value="1">角色</option>
				          	<option value="2">角色组</option>
				          </select>
				        </div>
				      </div>
				      <div class="form-group">
				        <label for="name" class="col-sm-2 control-label">角色名称</label>
				        <div class="col-sm-10">
				          <input type="text" class="form-control" id="name" name="name" placeholder="请输入角色名称....">
				        </div>
				      </div>
				      <div class="form-group">
				        <label for="father_id" class="col-sm-2 control-label">所属组</label>
				        <div class="col-sm-10">
				          <select id="father_id" name="father_id" class="form-control">
				          		<c:forEach var="roles" items="${fatherList}">
					          		<option value="${roles.id}">${roles.rolesName}</option>
					          	</c:forEach>
				          </select>
				        </div>
				      </div>
				      <div class="form-group">
				        <label for="mark" class="col-sm-2 control-label">拥有权限</label>
				        <div class="col-sm-10">
				        	<textarea id="treeTextArea" class="form-control" rows="5" cols="" style="resize:none;" readonly="readonly"></textarea>
				        	<div class="col-sm-11 ztree" id="treeDiv"></div>
				        </div>
				      </div>
				    </div>
				  </form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" id="subBtn">
					提交
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<script type="text/javascript" src="${ctx}/resource/js/zTree_v3/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/zTree_v3/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/paginator/bootstrap-paginator.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/roles/roles.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	if('${roleList.countPage}' != '0'){
		$('#pageLimit').bootstrapPaginator({
		    currentPage: "${currentPage}",
		    totalPages: "${roleList.countPage}",
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
		    	window.location.href='${ctx}/roles/listRoles?pageNum='+page+'';
		    }
		});
	}
});
</script>
</body>
</html>