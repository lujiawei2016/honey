<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>歪秀购物</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ui.css">
</head>
<body>
<div class="header">
	<div class="header-background"></div>
	<div class="toolbar statusbar-padding">
		<button class="bar-button back-button" onclick="history.go(-1);" dwz-event-on-click="click"><i class="icon icon-back"></i></button>
		<div class="header-title">
			<div class="title">新品上架</div>
		</div>
	</div>
</div>
<div style="height:44px"></div>
<div class="aui-it-content">
	<div class="aui-it-list">
		<h3 class="b-line">新品专区</h3>
		<ul>
			<li>
				<a href="javascript:;">
					<div class="aui-it-title"><img src="${ctx}/resource/images/main/b8.jpg" width="90"></div>
					<div class="aui-it-middle">
						<h2 style="padding-bottom:0;">拼色夹克棒球服短款外套女潮</h2>
						<h3 style="color:#ff5a00;padding:0; margin:0;">￥299.00</h3>
						<span class="aui-aui-s">新品</span>
						<span class="aui-aui-s">红包</span>
						<span class="aui-aui-s">折扣</span>
						<p class="aui-spill">韩版高端大牌立体花朵刺绣拼色夹克棒潮</p>
					</div>
				</a>
			</li>
			<li>
				<a href="javascript:;">
					<div class="aui-it-title"><img src="${ctx}/resource/images/main/b8.jpg" width="90"></div>
					<div class="aui-it-middle">
						<h2 style="padding-bottom:0;">拼色夹克棒球服短款外套女潮</h2>
						<h3 style="color:#ff5a00;padding:0; margin:0;">￥299.00</h3>
						<span class="aui-aui-s">新品</span>
						<span class="aui-aui-s">红包</span>
						<span class="aui-aui-s">折扣</span>
						<p class="aui-spill">韩版高端大牌立体花朵刺绣拼色夹克棒潮</p>
					</div>
				</a>
			</li>
		</ul>
	</div>
</div>


</body></html>