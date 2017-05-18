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
</head>
<body>

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
												<a href="javascript:;">
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
		<%-- <div class="b-line">
			<a class="home-inform aui-home-inform" data-href="home-slogan.html" target="navView" rel="slogan">
				<i class="name icon-inform"></i>
				<span style="font-size:14px; padding-left:5px">APP新版本上线换一种方式购物</span>
			</a>
		</div>
		<div class="my-car-thumbnail"><img src="${ctx}/resource/images/main/banner-car.jpg"></div> --%>

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
		<%-- <div class="aui-title-h">
			<h2>新品必购</h2>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1">
		<span>
			<img src="${ctx}/resource/images/main/xiao1.jpg">
		</span>
			</div>
			<div class="aui-flex-item aui-flex-items1">
		<span>
			<img src="${ctx}/resource/images/main/xiao2.jpg">
		</span>

			</div>
			<div class="aui-flex-item aui-flex-items1">
		<span>
			<img src="${ctx}/resource/images/main/xiao3.jpg">
		</span>

			</div>
			<div class="aui-flex-item aui-flex-items1">
		<span>
			<img src="${ctx}/resource/images/main/xiao34.jpg">
		</span>

			</div>
		</div> --%>
		<div class="aui-title-h">
			<h2>新品上架</h2>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b4.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b5.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b7.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/page.html" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b6.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b8.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b4.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b4.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b4.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b4.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<img src="${ctx}/resource/images/main/b4.jpg">
		</span>
				<a href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html?from=singlemessage#" class="aui-flex-box">
					<h2>依依衣舍韩版2017春装新款女装宽松显瘦直筒裤破洞牛仔裤HH5296妠 </h2>
					<em>￥199.00</em>
				</a>
			</div>
		</div>
		<div style="height:44px"></div>
		<div class="tab-bar tab-bottom">
			<a class="tab-button active" href="http://www.17sucai.com/preview/10221/2017-03-14/show/index.html"><i class="tab-button-icon icon icon-home"></i><span class="tab-button-txt">首页</span></a>
			<a class="tab-button cached" href="http://www.17sucai.com/preview/10221/2017-03-14/show/life.html"><i class="tab-button-icon icon icon-exhibition"></i><span class="tab-button-txt">生活</span></a>
			<a class="tab-button cached" href="http://www.17sucai.com/preview/10221/2017-03-14/show/classs.html"><i class="tab-button-icon icon icon-service"></i><span class="tab-button-txt">分类</span></a>
			<a class="tab-button cached" href="http://www.17sucai.com/preview/10221/2017-03-14/show/car.html"><i class="tab-button-icon icon icon-car"></i><span class="tab-button-txt">购物车</span></a>
			<a class="tab-button cached" href="http://www.17sucai.com/preview/10221/2017-03-14/show/me.html"><i class="tab-button-icon icon icon-my"></i><span class="tab-button-txt">我的</span></a>
		</div>
	</div>
</div>


<script type="text/javascript" src="${ctx}/resource/js/jquery/jQuery-2.1.4.min.js"></script>
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
<script src="${ctx}/resource/js/main/aui-swipe.js" type="text/javascript" charset="utf-8"></script>


</body></html>