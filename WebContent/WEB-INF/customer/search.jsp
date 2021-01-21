<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="user.UserBean" id="user" scope="session" />
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
        <h2>検索条件</h2>
        <form action="CustomerServlet" method="post">
            <table border="1">
                <tr>
                    <td class="title">氏名</td>
                    <td><input type="text" name="search">
                        <button name="state" value="list">検索</button></td>
                </tr>
            </table>
            <p>
                <button name="state" value="top" formaction="LoginServlet">メニュー画面</button>
            </p>
        </form>
    </div>
</body>
</html>
