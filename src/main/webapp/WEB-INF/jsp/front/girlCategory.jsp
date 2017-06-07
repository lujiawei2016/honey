<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>${category.cateName}妹纸专区</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ui.css">
<style type="text/css">
.loadDiv{width: 100%;height: 32px;line-height: 32px;text-align: center;}
</style>
</head>
<body>
<input type="hidden" id="path" value="${ctx}">
<input type="hidden" value="${category.categoryId}" id="categoryId">
<input type="hidden" id="returnFlag" value="1">
<div class="header">
	<div class="header-background"></div>
	<div class="toolbar statusbar-padding">
		<button class="bar-button back-button" onclick="history.go(-1);" dwz-event-on-click="click"><i class="icon icon-back"></i></button>
		<div class="header-title">
			<div class="title">${category.cateName}妹纸专区</div>
		</div>
	</div>
</div>
<div style="height:44px"></div>
<div class="aui-it-content">
	<div class="aui-it-list">
		<h3 class="b-line">${category.cateName}妹纸专区</h3>
		<ul>
			<c:forEach var="girl" items="${categoryDetailList}">
				<li>
					<a href="${ctx}/detail/girlDetail/${girl.girlId}">
						<div class="aui-it-title"><img src="${ctx}/${girl.mainImg}" width="90"></div>
						<div class="aui-it-middle">
							<h2 style="padding-bottom:0;">${girl.girlName}</h2>
							<h3 style="color:#ff5a00;padding:0; margin:0;">￥${girl.price}</h3>
							<span class="aui-aui-s">新品</span>
							<span class="aui-aui-s">红包</span>
							<span class="aui-aui-s">折扣</span>
							<p class="aui-spill">${girl.title}</p>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="loadDiv">
	<img alt="正在加载..." src="${ctx}/resource/images/load.gif">
</div>
<script type="text/javascript" src="${ctx}/resource/js/jquery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/layer.mobile-v2.0/layer.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/girlCategory/girlCategory.js"></script>

</body></html>