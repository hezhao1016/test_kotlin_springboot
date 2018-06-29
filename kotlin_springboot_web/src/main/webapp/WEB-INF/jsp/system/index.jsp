<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    
    <title>Index</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  
	<script src="<%=request.getContextPath()%>/static/js/jquery-2.0.3.min.js"></script>
	<script>
		$(function () {
			$("#btn").click(function () {
				alert("Info...");
            });
        });
	</script>
  </head>
  
  <body>
  	<h1>This is My JSP page.</h1>

	<a href="/page/redirect">redirect</a>
	<a href="/page/model">model</a>

  	<p><button id="btn">Click</button></p>
  </body>
</html>