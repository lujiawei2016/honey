<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>高端</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/js/swiper/swiper.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/vip.css">
</head>
<body>
    <header class="mui-bar mui-bar-nav aui-header b-line">
        <h1 class="mui-title">高端</h1>
    </header>
    <div class="mui-title-text">
        <p>绿色公益 权威发布</p>
    </div>
    <div class="swiper-container">
        <div class="swiper-wrapper">
        	<c:forEach var="girl" items="${girlList}">
	            <div class="swiper-slide" style="background-image:url(${ctx}/${girl.mainImg})">
	                <a href="${ctx}/detail/girlDetail/${girl.girlId}" target="_blank">
	                    <h1>${girl.girlName}</h1>
	                </a>
	            </div>
            </c:forEach>
        </div>
    </div>
    
    <nav class="mui-bar mui-bar-tab">
        <div class="t-line">
            <a href="${ctx}/main/index" class="aui-tab-item ">
                <span class="mui-icon mui-icon-home"></span>
                <span class="mui-tab-label">首页</span>
            </a>
            <a href="javascript:;" class="aui-tab-item mui-active">
                <span class="mui-icon mui-icon-extra mui-icon-extra-class"></span>
                <span class="mui-tab-label">高端</span>
            </a>
            <a class="aui-tab-item" href="#">
                <span class="mui-icon mui-icon-extra mui-icon-extra-cart"></span>
                <span class="mui-tab-label">绿色商城</span>
            </a>
            <a class="aui-tab-item" href="${ctx}/car/listCar">
                <span class="mui-icon mui-icon-extra mui-icon-extra-news"></span>
                <span class="mui-tab-label">备战区</span>
            </a>
            <a class="aui-tab-item" href="#">
                <span class="mui-icon mui-icon-extra mui-icon-extra-people"></span>
                <span class="mui-tab-label">我的</span>
            </a>
        </div>
    </nav>
    
    <script type="text/javascript" src="${ctx}/resource/js/jquery/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${ctx}/resource/js/swiper/swiper.min.js"></script>
    <script type="text/javascript" src="${ctx}/resource/js/vip/vip.js"></script>
</body>
</html>
