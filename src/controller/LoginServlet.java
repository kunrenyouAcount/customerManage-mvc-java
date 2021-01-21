package controller;

import static constants.MessageConstants.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserBean;
import user.UserLogic;
import util.LogUtil;
import util.StringUtil;

/**
 * ログイン管理のサーブレット
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @see BaseServlet#BaseServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * HTTPのGETメソッド受信時に呼び出される処理
     * <pre>
     * ログイン前の処理を行う
     * </pre>
     * @see BaseServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogUtil.println("**** " + this.getClass().getSimpleName() + "#doGet *****");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user != null) {
            procSessionError(request, response, session);
            return;
        }

        String state = request.getParameter("state");
        LogUtil.println("state: " + state);

        if (state == null) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login/login_form.jsp").forward(request, response);
            return;
        }

        procSessionError(request, response, session);
        return;
    }

    /**
     * HTTPのPOSTメソッド受信時に呼び出される処理
     * <pre>
     * セッションに含まれるstate属性の値に応じて
     * ログイン管理固有の処理を行う
     * </pre>
     * @see BaseServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        LogUtil.println("**** " + this.getClass().getSimpleName() + "#doPost *****");

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String state = request.getParameter("state");
        LogUtil.println("state: " + state);

        if (state == null) {
            procSessionError(request, response, session);
            return;
        }

        UserBean user = (UserBean) session.getAttribute("user");

        switch (state) {
        case "login_process":
            procLoginProcess(request, response, session, user);
            break;
        case "top":
            procTop(request, response, session, user);
            break;
        case "logout":
            procLogout(request, response, session, user);
            break;
        }
    }

    /**
     * リクエスト内のログイン情報と、DBのユーザ情報を比較し、メニュー画面(ログイン成功)、または、ログイン失敗画面に遷移する
     * <pre>
     * state属性＝"login_process"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報(通常はログイン前のためnullとなる)
     */
    private void procLoginProcess(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            UserBean user) throws ServletException,
            IOException {

        if (user != null) {
            session.setAttribute("errMessage", MESSAGE_ALREADY_LOGGED_IN);
            getServletContext().getRequestDispatcher("/WEB-INF/login/login_error.jsp").forward(request, response);
            return;
        }

        String strLogin = StringUtil.exchangeESCEncoding(request.getParameter("login"));
        String strPassword = StringUtil.exchangeESCEncoding(request.getParameter("password"));

        UserLogic userLogic = new UserLogic();
        UserBean loginUser = userLogic.login(strLogin, strPassword);
        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            getServletContext().getRequestDispatcher("/WEB-INF/login/menu.jsp").forward(request, response);
        } else {
            session.setAttribute("errMessage", MESSAGE_NO_EXIST_CORRESPOND_DATA);
            getServletContext().getRequestDispatcher("/WEB-INF/login/login_error.jsp").forward(request, response);
        }
    }

    /**
     * ログイン中のユーザ情報をDBから取得し直し、メニュー画面に遷移する
     * <pre>
     * state属性＝"top"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procTop(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserBean user)
            throws ServletException, IOException {
        if (user == null || user.getName() == null) {
            procSessionError(request, response, session);
            return;
        }

        UserLogic userLogic = new UserLogic();
        user = userLogic.load(user.getId());
        session.setAttribute("user", user);

        getServletContext().getRequestDispatcher("/WEB-INF/login/menu.jsp").forward(request, response);
    }

    /**
     * セッションを破棄し、ログアウト画面に遷移する
     * <pre>
     * state属性＝"logout"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserBean user)
            throws ServletException,
            IOException {
        if ((user == null) || (user.getName() == null)) {
            procSessionError(request, response, session);
            return;
        }

        session.invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/login/logout.jsp").forward(request, response);
    }
}
