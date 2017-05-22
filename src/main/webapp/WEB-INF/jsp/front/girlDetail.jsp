<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en" style="font-size: 100px;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>${dataMap.girl.girlName}</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/girlDetail.css">
</head>
<body class="item">
<input type="hidden" id="path" value="${ctx}" />
<input type="hidden" id="girlId" value="${dataMap.girl.girlId}">
<div class="header">
	<div class="" style="background:none"></div>
	<div class="toolbar statusbar-padding">
		<button class="bar-button back-button" onclick="history.go(-1);" dwz-event-on-click="click"><i class="icon icon-back-i"></i></button>
		<div class="header-title">
			<div class="title"></div>
		</div>
	</div>
</div>

<!-- 首页轮播 begin -->
<div class="aui-banner-content">
	<div id="focus" class="focus">
		<div class="bd">
			<div class="tempWrap" style="overflow:hidden; position:relative;">
				<div class="tempWrap" style="overflow:hidden; position:relative;">
					<ul id="Gallery" class="gallery" style="width: 8094px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translate(-5396px, 0px) translateZ(0px);">
						<c:forEach begin="0" end="2" varStatus="vs">
							<li style="display: table-cell; vertical-align: top; width: 1349px;">
								<a href="javascript:;">
									<img alt="${dataMap.girl.girlName}" src="${ctx}/${dataMap.girlImgList[vs.index].imgUrl}">
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="hd">
			<ul>
				<c:forEach begin="0" end="2" varStatus="vs">
					<li class="on">${vs.index+1}</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<!-- 首页轮播 end -->

<section class="header" style="position:inherit">
	<h2 class="title">${dataMap.girl.title}</h2>
	<div class="price ">
		<div class="current-price">
			<span class="current-price"><small>￥</small>${dataMap.girl.price}</span>
		</div>
		<span class="express">¥${dataMap.girl.price}</span>
	</div>
	<div class="sales">年龄: ${dataMap.girl.age} 身高: ${dataMap.girl.hight}</div>
</section>

<section class="sku">
	<dl class="sku-group">
		<dt>点赞数: </dt>
		<dd>${dataMap.girl.praise}</dd>
	</dl>
</section>

<section class="content">
	<div class="nav">
		<a href="javascript:;" class="active goodsDetail">商品详细</a>
		<a href="javascript:;" class="preSale">简介须知</a>
	</div>
	<div class="desc goodsDetailDiv">
		<c:forEach var="girlImg" items="${dataMap.girlImgList}">
			<img alt="${dataMap.girl.girlName}" src="${ctx}/${girlImg.imgUrl}" width="100%">
		</c:forEach>
	</div>
	<div class="desc preSaleDiv">
		<div class="mainImgDiv">
			<img alt="${dataMap.girl.girlName}" src="${ctx}/${dataMap.girl.mainImg}" width="100%">
		</div>
		<div class="introduceDiv">
			<ul>
				<li>
					<label>姓名</label> ${dataMap.girl.girlName}
				</li>
				<li>
					<label>年龄</label> ${dataMap.girl.age}
				</li>
				<li>
					<label>身高</label> ${dataMap.girl.hight}
				</li>
				<li>
					<label>体重</label> ${dataMap.girl.weight}
				</li>
				<li>
					<label>QQ</label> ${dataMap.girl.qq}
				</li>
				<li>
					<label>微信</label> ${dataMap.girl.weixin}
				</li>
				<li>
					<label>电话</label> ${dataMap.girl.phone}
				</li>
				<li>
					<label>地址</label> ${dataMap.girl.address}
				</li>
				<%-- <li style="overflow:auto">
					<label>描述</label> ${dataMap.girl.description}
				</li> --%>
			</ul>
		</div>
	</div>

</section>


<footer class="footer t-line">
	<div class="aui-car-ear">
		<div class="aui-car-ear-cell">
			<div class="aui-li">
				<a href="javascript:;" class="aui-icon-gz"></a>
				<a href="javascript:;"></a>
				<a href="${ctx}/car/listCar" class="aui-icon-gw"></a>
			</div>
		</div>
		<div class=""><a href="javascript:;" class="aui-car-all fighting">加入备战区</a></div>
		<div class=""><a href="${ctx}/car/listCar" class="aui-car-lli">查看备战区</a></div>
	</div>

</footer>

<script type="text/javascript" src="${ctx}/resource/js/jquery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/main/swiper.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/main/jquery.lazyload.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/main/aui-touchSlide.js"></script>
<script>
	/*banner首页轮播*/
	TouchSlide({
		slideCell : "#focus",
		titCell : ".hd ul", // 开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		mainCell : ".bd ul",
		effect : "leftLoop",
		autoPlay : true, // 自动播放
		autoPage : true, // 自动分页
		delayTime: 200, // 毫秒；切换效果持续时间（执行一次效果用多少毫秒）
		interTime: 5000, // 毫秒；自动运行间隔（隔多少毫秒后执行下一个效果）
		switchLoad : "_src" // 切换加载，真实图片路径为"_src"
	});
</script>
<script type="text/javascript" src="${ctx}/resource/js/main/aui-swipe.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/resource/js/girlDetail/girlDetail.js"></script>
</body></html>