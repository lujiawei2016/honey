<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>待约区</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ui.css">
</head>
<body>
<input type="hidden" id="path" value="${ctx}" />
<input type="hidden" id="girlIds" />
<div class="aui-container">
	<div class="aui-page">
		<div class="header">
			<div class="header-background"></div>
			<div class="toolbar statusbar-padding">
				<button class="bar-button back-button" onclick="history.go(-1);" dwz-event-on-click="click"><i class="icon icon-back"></i></button>
				<div class="header-title">
					<div class="title">待约区</div>
				</div>
			</div>
		</div>
		<div style="height:50px"></div>
		<div class="main_con">
			<div class="main_con_allchoose ">
				<a href="javascript:;" class="delCar"><img src="${ctx}/resource/images/main/delete-icon.png"></a>
			</div>
			<div class="main_con_goods">
				<ul>
					<c:forEach var="girl" items="${carList}">
						<li>
							<section class="aui-crl">
								<span class="circle" id="circle${girl.girlId}"></span>
								<img src="${ctx}/${girl.mainImg}">
							</section>
							<div style="width: 1199px; padding-left: 10px;">
								<h2>${girl.title}</h2>
								<p class="aui-o">年龄:${girl.age},身高:${girl.hight}</p>
								<p class="money"><em class="aui-redd">${girl.price}￥</em>
									<input class="add" type="button" value="">
									<input class="num" type="number" value="1">
									<input class="del" type="button" value="">
								</p>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div style="height:1px"></div>
		<!--内容信息 end-->

		<!--结算信息 start-->
		<div class="settlement t-line">
			<div class="settlement_left">
				<font class="zongji">总计：</font><font class="money totalmoney">￥0</font><br>
				（共<span class="girlNum">0</span>件，不含运费）
			</div>
			<div class="settlement_right">
				<a href="javascript:;" class="settlementBtn">约会金额</a>
			</div>
		</div>
		<!--结算信息 end-->
		
	</div>
</div>


<script type="text/javascript" src="${ctx}/resource/js/jquery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/layer.mobile-v2.0/layer.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/car/car.js"></script>
</body></html>