<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="customer.*"%>
<%@ page import="user.*"%>
<jsp:useBean class="user.UserBean" id="user" scope="session" />
<jsp:useBean class="user.UserListBean" id="userList" scope="request" />
<%
    String[] aryLevelName = { "一般ユーザー", "顧客管理者", "システム管理者" };
%>
<!doctype html>
<html>
<head>
<title>ユーザー管理</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>ユーザー管理</h1>
    <div class="main">
        <h2>ユーザー一覧</h2>
        <table>
            <tr>
                <th>ＩＤ</th>
                <th>ログインID</th>
                <th>氏名</th>
                <th>ユーザーレベル</th>
                <th>操作</th>
            </tr>
            <%
                while (userList.hasNext()) {
                    UserBean anUser = userList.getNext();
            %>
            <tr>
                <td class="center"><%=anUser.getId()%></td>
                <td><%=anUser.getLogin()%></td>
                <td><%=anUser.getName()%></td>
                <td><%=aryLevelName[anUser.getLvl()]%></td>
                <td class="center">
                    <form action="UserServlet" method="post">
                        <button name="state" value="edit,<%=anUser.getId()%>">編集･削除</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <form action="LoginServlet" method="post">
            <p>
                <button name="state" value="new" formaction="UserServlet">新規登録</button>
                <button name="state" value="top">メニュー画面</button>
                <button name="state" value="logout">ログアウト</button>
            </p>
        </form>
    </div>
</body>
</html>

