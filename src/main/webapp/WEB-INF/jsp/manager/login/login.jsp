<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${ctx}/resource/css/login.css" />
<link rel="stylesheet" href="${ctx}/resource/css/drag.css" />
<title>系统登录</title>
</head>
<body>
<input type="hidden" id="path" value="${ctx}" />
<div class="login-box">
	<h1>系统登录</h1>
	<form method="post" action="">
		<div class="name">
			<label>管理员账号：</label>
			<input type="text" name="username" id="username" tabindex="1" autofocus="autofocus" />
		</div>
		<div class="password">
			<label>密  码：</label>
			<input type="password" name="password" maxlength="16" id="password" tabindex="2"/>
			<input type="hidden" id="verificationCode" />
		</div>
		<div class="code">
			<label>验证码：</label>
			<div class="codeImg">
				<div id="drag"></div>
			</div>
		</div>
		<div class="login">
			<button type="button" tabindex="5" id="loginBtn">登录</button>
		</div>
	</form>
</div>

<div class="screenbg">
	<ul>
		<li><a href="javascript:;"><img src="${ctx}/resource/images/loginBackground.jpg"></a></li>
	</ul>
</div>

<script type="text/javascript" src="${ctx}/resource/js/jquery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/layer-v3.0.3/layer.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/drag.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/login.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//根据窗口宽度生成图片宽度
	var width = $(window).width();
	$(".screenbg ul img").css("width",width+"px");
});
</script>

</body>
</html>
