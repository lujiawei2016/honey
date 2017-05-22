<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<!-- saved from url=(0061)http://www.17sucai.com/preview/10221/2017-03-14/show/car.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>歪秀购物</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ui.css">
</head>
<body>

<div class="aui-container">
	<div class="aui-page">
		<div class="header">
			<div class="header-background"></div>
			<div class="toolbar statusbar-padding">
				<button class="bar-button back-button" onclick="history.go(-1);" dwz-event-on-click="click"><i class="icon icon-back"></i></button>
				<div class="header-title">
					<div class="title">购物车</div>
				</div>
			</div>
		</div>
		<div style="height:50px"></div>
		<div class="main_con">
			<div class="main_con_allchoose ">
				<span class="circle"></span>全选 <a href="javascript:del_goods()"><img src="${ctx}/resource/images/main/delete-icon.png"></a>
			</div>
			<div class="main_con_goods">
				<ul>
					<li>
						<section class="aui-crl">
							<span class="circle"></span>
							<img src="${ctx}/resource/images/main/b4.jpg">
						</section>
						<div style="width: 1199px; padding-left: 10px;">
							<h2>2017春装新款时尚女装宽松贴布开衫针织衫NG5836</h2>
							<p class="aui-o">颜色分类:黑色,尺码:M</p>
							<p class="money"><em class="aui-redd">60￥</em>
								<input class="add" type="button" value="">
								<input class="num" type="number" value="1">
								<input class="del" type="button" value="">
							</p>
						</div>
					</li>
					<li>
						<section class="aui-crl">
							<span class="circle"></span>
							<img src="${ctx}/resource/images/main/b5.jpg">
						</section>
						<div style="width: 1199px; padding-left: 10px;">
							<h2>2017春装新款时尚女装宽松贴布开衫针织衫NG5836</h2>
							<p class="aui-o">颜色分类:黑色,尺码:M</p>
							<p class="money"><em class="aui-redd">230￥</em>
								<input class="add" type="button" value="">
								<input class="num" type="number" value="1">
								<input class="del" type="button" value="">
							</p>
						</div>
					</li>
					<li>
						<section class="aui-crl">
							<span class="circle"></span>
							<img src="${ctx}/resource/images/main/b6.jpg">
						</section>
						<div style="width: 1199px; padding-left: 10px;">
							<h2>2017春装新款时尚女装宽松贴布开衫针织衫NG5836</h2>
							<p class="aui-o">颜色分类:黑色,尺码:M</p>
							<p class="money"><em class="aui-redd">260￥</em>
								<input class="add" type="button" value="">
								<input class="num" type="number" value="1">
								<input class="del" type="button" value="">
							</p>
						</div>
					</li>
					<li>
						<section class="aui-crl">
							<span class="circle"></span>
							<img src="${ctx}/resource/images/main/b7.jpg">
						</section>
						<div style="width: 1199px; padding-left: 10px;">
							<h2>2017春装新款时尚女装宽松贴布开衫针织衫NG5836</h2>
							<p class="aui-o">颜色分类:黑色,尺码:M</p>
							<p class="money"><em class="aui-redd">60￥</em>
								<input class="add" type="button" value="">
								<input class="num" type="number" value="1">
								<input class="del" type="button" value="">
							</p>
						</div>
					</li>
					<li>
						<section class="aui-crl">
							<span class="circle"></span>
							<img src="${ctx}/resource/images/main/b8.jpg">
						</section>
						<div style="width: 1199px; padding-left: 10px;">
							<h2>2017春装新款时尚女装宽松贴布开衫针织衫NG5836</h2>
							<p class="aui-o">颜色分类:黑色,尺码:M</p>
							<p class="money"><em class="aui-redd">450￥</em>
								<input class="add" type="button" value="">
								<input class="num" type="number" value="1">
								<input class="del" type="button" value="">
							</p>
						</div>
					</li>
					<li>
						<section class="aui-crl">
							<span class="circle"></span>
							<img src="${ctx}/resource/images/main/b4.jpg">
						</section>
						<div style="width: 1199px; padding-left: 10px;">
							<h2>2017春装新款时尚女装宽松贴布开衫针织衫NG5836</h2>
							<p class="aui-o">颜色分类:黑色,尺码:M</p>
							<p class="money"><em class="aui-redd">90￥</em>
								<input class="add" type="button" value="">
								<input class="num" type="number" value="1">
								<input class="del" type="button" value="">
							</p>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div style="height:1px"></div>
		<!--内容信息 end-->

		<!--结算信息 start-->
		<div class="settlement t-line">
			<div class="settlement_left">
				<font class="zongji">总计：</font><font class="money">￥0</font><br>
				（共0件，不含运费）
			</div>
			<div class="settlement_right">
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/confirm.html">去结算</a>
			</div>
		</div>
		<!--结算信息 end-->

		<!--删除弹窗 start-->
		<div class="del_tc" style="height: 638px;">
			<div class="del_tc_box">
				<div class="del_tc_box_title">
					确认删除此商品？
				</div>
				<div class="del_tc_box_content">
					<input class="cancel" type="button" value="取消" onclick="del_goods_cancel()">
					<input class="ok" type="button" value="确认">
				</div>
			</div>
		</div>
		<!--删除弹窗 end-->



		<!--无商品页面 start-->
		<div class="no_goods" style="height: 599.72px;">
			<img src="${ctx}/resource/images/main/no_goods_ico.png">
			<p>购物车里没有东西哦~<br>快去商城逛逛吧！</p>
			<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/car.html">即刻添置</a>
		</div>
		<!--无商品页面 end-->
	</div>
</div>


<script type="text/javascript" src="${ctx}/resource/js/jquery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/main/car-js.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/main/car-mi.js"></script>

</body></html>