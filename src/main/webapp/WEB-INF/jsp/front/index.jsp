<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<!-- saved from url=(0082)http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>幸福生活</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ui.css">
	<style type="text/css">
	.loadDiv{width: 100%;height: 32px;line-height: 32px;text-align: center;}
	</style>
</head>
<body>
<input type="hidden" id="path" value="${ctx}">
<input type="hidden" id="returnFlag" value="1">
<div class="aui-container">
	<div class="aui-page">
		<!-- 头部 begin-->
		<div class="header">
			<div class="aui-header-bg" style="background:#ff5a5f;"></div>
			<div class="toolbar statusbar-padding" style="min-height:50px">
				<button class="bar-button back-button"><i class="icon icon-sao"></i></button>
				<div class="header-title" style="height:50px; padding:0 50px">
					<div class="title aui-title-input"><input type="text" placeholder="秋季新品"></div>
				</div>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/news.html">
					<button class="icon aui-icon-mag"></button>
				</a>
			</div>
		</div>
		<div style="height:50px"></div>
		<!-- 头部 End-->
		<!-- 首页轮播 begin -->
		<div class="aui-banner-content">
			<div id="focus" class="focus">
				<div class="bd">
					<div class="tempWrap" style="overflow:hidden; position:relative;">
						<div class="tempWrap" style="overflow:hidden; position:relative;">
							<ul id="Gallery" class="gallery" style="width: 8094px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translate(-4047px, 0px) translateZ(0px);">
								<c:choose>
									<c:when test="${empty bannerList}">
										<li style="display: table-cell; vertical-align: top; width: 1349px;">
											<a href="javascript:;"><img src="${ctx}/resource/images/main/banner1.jpg"></a>
										</li>
									</c:when>
									<c:otherwise>
										<c:forEach var="banner" items="${bannerList}">
											<li style="display: table-cell; vertical-align: top; width: 1349px;">
												<a href="${banner.url}">
													<img alt="${banner.imgName}" src="${ctx}/${banner.imgUrl}">
												</a>
											</li>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>
				<div class="hd">
					<ul><li class="">1</li><li class="">2</li><li class="on">3</li><li class="">4</li></ul>
				</div>
			</div>
		</div>
		<!-- 首页轮播 end -->
		<!-- 分类切换 begin -->
		<div class="" id="container" style="top: 50px;">
			<div id="main" style="transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(0px, 0px) translateZ(0px);">
				<div id="scroller">
					<section class="slider" style="margin:0  auto; width:100%">
						<div class="swiper-container swiper-container2 swiper-container-horizontal">
							<div class="swiper-wrapper tuangouwidth" style="transition-duration: 0ms; transform: translate3d( 0px, 0px);">
								<div class="swiper-slide swiper-slide-duplicate swiper-slide-active" style="width: 100%;">
									<ul class="icon-list">
										<c:forEach var="category" items="${categoryList}">
											<li class="icon">
												<a href="${ctx}/categoryDetail/listGirlCategory/${category.categoryId}/0/19">
													<span class="icon-circle"><img src="${ctx}/${category.imgUrl}"></span>
													<span class="icon-desc">${category.cateName}</span>
												</a>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
		<!-- 分类切换 end -->
		<div class="devider t-line"></div>
		<div class="b-line" style="position:relative"></div>

		<div class="aui-title-h">
			<h2>大牌热卖</h2>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items">
				<span>
					<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/page.html"><img src="${ctx}/resource/images/main/b1.jpg"></a>
				</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">新款上市</a>
			</div>
			<div class="aui-flex-item aui-flex-items">
				<span>
					<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/page.html"><img src="${ctx}/resource/images/main/b2.jpg"></a>
				</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">爱美装扮</a>

			</div>
			<div class="aui-flex-item aui-flex-items">
				<span>
					<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/page.html"><img src="${ctx}/resource/images/main/b3.jpg"></a>
				</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">热潮时代</a>
			</div>
		</div>
		<div class="aui-title-h">
			<h2>新品上架</h2>
		</div>
		
		<div class="loadDiv">
			<img alt="正在加载..." src="${ctx}/resource/images/load.gif">
		</div>
		<div style="height:44px" class="insertFlag"></div>
		<div class="tab-bar tab-bottom">
			<a class="tab-button active" href="javascript:;"><i class="tab-button-icon icon icon-home"></i><span class="tab-button-txt">首页</span></a>
			<a class="tab-button cached" href="${ctx}/vip/listVip"><i class="tab-button-icon icon icon-exhibition"></i><span class="tab-button-txt">高端</span></a>
			<a class="tab-button cached" href="${ctx}/car/listCar"><i class="tab-button-icon icon icon-service"></i><span class="tab-button-txt">分类</span></a>
			<a class="tab-button cached" href="${ctx}/car/listCar"><i class="tab-button-icon icon icon-car"></i><span class="tab-button-txt">备战区</span></a>
			<a class="tab-button cached" href="http://www.17sucai.com/preview/10221/2017-03-14/show/me.html"><i class="tab-button-icon icon icon-my"></i><span class="tab-button-txt">我的</span></a>
		</div>
	</div>
</div>


<script type="text/javascript" src="${ctx}/resource/js/jquery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/layer.mobile-v2.0/layer.js"></script>
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
<script type="text/javascript" src="${ctx}/resource/js/index/index.js"></script>

</body></html>