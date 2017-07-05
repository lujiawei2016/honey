<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>联系我们</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ui.css">
<title>联系我们</title>
</head>
<body>


<div class="aui-container">
	<div class="aui-page">
		<div class="header">
			<div class="header-background"></div>
			<div class="toolbar statusbar-padding">
				<button class="bar-button back-button" onclick="history.go(-1);" dwz-event-on-click="click"><i class="icon icon-back"></i></button>
				<div class="header-title">
					<div class="title">联系我们</div>
				</div>
			</div>
		</div>
		<div style="height:50px"></div>
		<div style="height:1px"></div>
		
		<img alt="让别人绿" src="${ctx}/resource/images/lv.png" width="100%">
		
		<div style="height:44px" class="insertFlag"></div>
		<div class="tab-bar tab-bottom">
			<a class="tab-button cached" href="${ctx}/main/index"><i class="tab-button-icon icon icon-home"></i><span class="tab-button-txt">首页</span></a>
			<a class="tab-button cached" href="${ctx}/vip/listVip"><i class="tab-button-icon icon icon-exhibition"></i><span class="tab-button-txt">热门</span></a>
			<a class="tab-button active" href="javascript:;"><i class="tab-button-icon icon icon-service"></i><span class="tab-button-txt">联系我们</span></a>
			<a class="tab-button cached" href="${ctx}/car/listCar"><i class="tab-button-icon icon icon-car"></i><span class="tab-button-txt">待约区</span></a>
			<a class="tab-button cached" href="${ctx}/my/myInfo"><i class="tab-button-icon icon icon-my"></i><span class="tab-button-txt">我的</span></a>
		</div>
		
	</div>
</div>

</body>
</html>