<%@ page contentType="text/html;charset=UTF-8" %>
 <%
 	String basePath = request.getContextPath();
 %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>西南交大犀浦社区</title>
</head>
<body>
	这是swjtucomnty西南交大犀浦社区首页
	<form action="<%=basePath %>/myServlet" method="POST">
		标题：<input type="text" name="title" value="唐荣真棒"/><br>
		内容：
		<textarea name="content" rows="10" cols="10">
			你真是个大骗子啊。大骗子啊，大骗子！！
		</textarea>
		<input type="submit" name="submit" value="提交"/>
	</form>
</body>
</html>