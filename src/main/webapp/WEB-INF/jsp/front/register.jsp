<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册用户</title>
<meta name="viewport" content="width=device-width"/>
<meta name="viewport" content="initial-scale=1.0,user-scalable=no"/>

<link rel="stylesheet" href="${ctx}/resource/js/fontlogin/css/style.css" type="text/css" />

</head>

<body>

<div class="cont">
  <div class="demo">
    <div class="login">
      <div class="login__check"></div>
      <div class="login__form">
        <div class="login__row">
          <svg class="login__icon name svg-icon" viewBox="0 0 20 20">
            <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8" />
          </svg>
          <input type="text" id="username" name="username" class="login__input name" placeholder="请输入用户名"/>
        </div>
        <div class="login__row">
          <svg class="login__icon pass svg-icon" viewBox="0 0 20 20">
            <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
          </svg>
          <input type="password" class="login__input pass" placeholder="请输入密码"/>
        </div>
        <div class="login__row">
          <svg class="login__icon pass svg-icon" viewBox="0 0 20 20">
            <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
          </svg>
          <input type="password" class="login__input pass" placeholder="请再次输入密码"/>
        </div>
        <button type="button" class="login__submit" style="margin: 1rem 0 2.2rem">注 册</button>
        <p class="login__signup">已经有账号？ &nbsp;<a href="${ctx}/userLogin/loginGirl">立刻登录</a></p>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src='${ctx}/resource/js/jquery/jQuery-2.1.4.min.js'></script>
<script type="text/javascript" src="${ctx}/resource/js/fontlogin/register.js"></script>
</body>
</html>
