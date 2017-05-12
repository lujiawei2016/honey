<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="${ctx}/resource/css/404style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="main">
		<!-- header -->
		<div id="header">
			<h1>地址错误或没有权限访问<span>404 error - not found.</span></h1>
		</div>
		<!-- content -->
		<div id="content">
			<ul class="nav">
         	<li class="home"><a href="javascript:;">Home Page</a></li>
            <li class="site_map"><a href="javascript:;">Site Map</a></li>
            <li class="search"><a href="javascript:;">Website Search</a></li>
         </ul>
         <p>Unless creepy emptiness was what you were looking for, this place has nothing that you want.<br /> 
         So please either go to our <a href="javascript:;">homepage</a>, <a href="javascript:;">sitemap</a> or try using the <a href="javascript:;">website search</a>.</p>
		</div>
		<!-- footer -->
		<div id="footer">
      	Designed by TemplateMonster - all <a href="http://www.cssmoban.com" target="_blank">网页模板</a> found and safe!
      </div>
	</div>
</body>
</html>