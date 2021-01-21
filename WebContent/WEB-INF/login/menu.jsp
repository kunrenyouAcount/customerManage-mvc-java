<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean class="user.UserBean" id="user" scope="session" />
<!doctype html>
<html>
<head>
<title>メニュー</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>メニュー</h1>
    <div class="main">
        <p>
            ログインユーザ：
            <%=user.getName()%><br />
        </p>
        <form method="post">
            <button class="menu" name="state" value="password_change_form"
                formaction="PasswordChangeServlet">パスワード変更</button>
            <button class="menu" name="state" value="search"
                formaction="CustomerServlet">顧客管理</button>
            <%
                if (user.getLvl() >= 1) {
            %>
            <button class="menu" name="state" value="customer_csv"
                formaction="CustomerCsvServlet">顧客管理（CSV）</button>
                        <%
                }
            %>
            <%
                if (user.getLvl() == 2) {
            %>
            <button class="menu" name="state" value="list" formaction="UserServlet">ユーザー管理</button>
            <%
                }
            %>
            <button class="menu" name="state" value="logout"
                formaction="LoginServlet">ログアウト</button>
        </form>
    </div>
</body>
</html>
