<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>个人信息</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ui.css">
</head>
<body class="android">
<input type="hidden" id="path" value="${ctx}" />
<div class="scroll-content">
	<div class="">
		<div class="header home-header" style="" id="headsearch">
			<div class="toolbar statusbar-padding active">
				<button class="bar-button current-city"><i class="icon icon-set" style="left:0;right: inherit"></i></button>

				<button class="bar-button icon-button"><i class="icon icon-msg"></i></button>
			</div>
		</div>
		<div class="my-info">
			<div class="my-info-background"></div>
			<img class="my-avatar" src="${ctx}/resource/images/main/my-photo1.jpg">
			<span class="name"><a href="javascript:;" style="color:#fff">${user.username}</a></span>
			<span class="my-vip" style="background:none">超级会员&nbsp;&nbsp;&nbsp;&nbsp;积分:9999&nbsp;&nbsp;</span>
		</div>
		<div class="my-car-shortcut">
			<div class="layout-column b-line" style="padding:10px 0">
				<a class="col" rel="test" href="javascript:;">
							<span class="img-icon ">
								<img class="img-icon-home" src="${ctx}/resource/images/main/me-1.png">
							</span>
					<span class="img-icon-name">待付款</span>
				</a>
				<a class="col" rel="test" href="javascript:;">
							<span class="img-icon ">
								<img class="img-icon-home" src="${ctx}/resource/images/main/me-2.png">
							</span>
					<span class="img-icon-name">待收货</span>
				</a>
				<a class="col" href="javascript:;" rel="test">
							<span class="img-icon ">
								<img class="img-icon-home" src="${ctx}/resource/images/main/me-3.png">
							</span>
					<span class="img-icon-name">全部订单</span>
				</a>
			</div>
		</div>
		<div class="devider b-line"></div>
		<!-- 个人中心 begin-->
		<div>
			<div class="aui-list-cells">
				<a href="javascript:;" class="aui-list-cell">
					<div class="aui-list-cell-fl"><img src="${ctx}/resource/images/main/me-4.png"></div>
					<div class="aui-list-cell-cn">我的钱包</div>
					<div class="aui-list-cell-fr">3999</div>
				</a>
				<a href="javascript:;" class="aui-list-cell">
					<div class="aui-list-cell-fl"><img src="${ctx}/resource/images/main/me-13.png"></div>
					<div class="aui-list-cell-cn">我的收藏</div>
					<div class="aui-list-cell-fr"></div>
				</a>
				<div class="devider b-line"></div>
				<a href="javascript:;" class="aui-list-cell exit">
					<div class="aui-list-cell-fl"><img src="${ctx}/resource/images/main/me-11.png"></div>
					<div class="aui-list-cell-cn">退出账号</div>
					<div class="aui-list-cell-fr"></div>
				</a>
			</div>
		</div>


	</div>
</div>
<div style="height:48px"></div>
<div class="tab-bar tab-bottom">
	<a class="tab-button cached" href="${ctx}/main/index"><i class="tab-button-icon icon icon-home"></i><span class="tab-button-txt">首页</span></a>
	<a class="tab-button cached" href="${ctx}/vip/listVip"><i class="tab-button-icon icon icon-exhibition"></i><span class="tab-button-txt">高端</span></a>
	<a class="tab-button cached" href="${ctx}/car/listCar"><i class="tab-button-icon icon icon-service"></i><span class="tab-button-txt">分类</span></a>
	<a class="tab-button cached" href="${ctx}/car/listCar"><i class="tab-button-icon icon icon-car"></i><span class="tab-button-txt">备战区</span></a>
	<a class="tab-button active" href="javascript:;"><i class="tab-button-icon icon icon-my"></i><span class="tab-button-txt">我的</span></a>
</div>
<script type="text/javascript" src="${ctx}/resource/js/jquery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/layer.mobile-v2.0/layer.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/my/my.js"></script>
</body></html>