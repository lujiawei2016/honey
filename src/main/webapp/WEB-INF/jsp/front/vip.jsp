<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>VIP专区</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/js/swiper/swiper.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/vip.css">
</head>
<body>
    <header class="mui-bar mui-bar-nav aui-header b-line">
        <h1 class="mui-title">VIP专区</h1>
    </header>
    <div class="mui-title-text">
        <p>绿色公益 权威发布</p>
    </div>
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/${ctx}/resource/images/img/1.jpg)">
                <a href="#" target="_blank">
                    <h1>周杰伦</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/2.jpg)">
                <a href="#" target="b">
                    <h1>潘玮柏</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/3.jpg)">
                <a href="#">
                    <h1>林俊杰</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/4.jpg)">
                <a href="#">
                    <h1>王力宏</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/5.jpg)">
                <a href="#">
                    <h1>张信哲</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/6.jpg)">
                <a href="#">
                    <h1>陈奕迅</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/7.jpg)">
                <a href="#">
                    <h1>吴克群</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/8.jpg)">
                <a href="#">
                    <h1>薛之谦</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/9.jpg)">
                <a href="#">
                    <h1>陈楚生</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/10.jpg)">
                <a href="#">
                    <h1>筷子兄弟</h1>
                </a>
            </div>

            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/20.jpg)">
                <a href="#">
                    <h1>范冰冰</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/21.jpg)">
                <a href="#">
                    <h1>刘诗诗</h1>
                </a>
            </div>
            <div class="swiper-slide" style="background-image:url(${ctx}/resource/images/img/22.jpg)">
                <a href="#">
                    <h1>唐嫣</h1>
                </a>
            </div>

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
                <span class="mui-tab-label">VIP专区</span>
            </a>
            <a class="aui-tab-item" href="#">
                <span class="mui-icon mui-icon-extra mui-icon-extra-cart"></span>
                <span class="mui-tab-label">绿色商城</span>
            </a>
            <a class="aui-tab-item" href="#">
                <span class="mui-icon mui-icon-extra mui-icon-extra-news"></span>
                <span class="mui-tab-label">消息</span>
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
