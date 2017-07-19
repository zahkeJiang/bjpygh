<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/idf.action" method="post">
		<p>驾校名称： <input type="text" name="dsname" /></p>
		<p>驾校图片： <input type="text" name="dsimage" /></p>
		<p>驾校简介： <input type="text" name="dsintro" /></p>
		<p>驾校地址： <input type="text" name="address" /></p>
		<input type="submit" value="提交" />
	</form>
	<form action="/ddi.action" method="post">
		<p>删除驾校 <input type="text" name="dsname" /></p>
		<input type="submit" value="删除" />
	</form>
</body>
</html>