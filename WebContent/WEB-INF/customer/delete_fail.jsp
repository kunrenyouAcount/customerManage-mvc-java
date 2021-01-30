<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String str =(String) request.getAttribute("errorMsg"); %>
<!doctype html>
<html>
<head>
<title>顧客管理</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>顧客管理</h1>
    <div class="main">
	    <h2>削除未完了</h2>
		<p><%=str %></p>
    <form action="CustomerServlet" method="post">
        <!-- TODO ｢削除未完了｣画面 -->
        <p><button type="submit" value="search" name="state">検索画面</button></p>
    </form>
    </div>
</body>
</html>