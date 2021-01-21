package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.LogUtil;

/**
 * サーブレットの基底クラス
 * Servlet implementation class BaseControllerServlet
 */
public abstract class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
    }

    /**
     * HTTPのGETメソッド受信時に呼び出される処理
     * <pre>
     * LoginServlet以外のサブクラスで共通の処理
     * LoginServletではオーバライドする
     * </pre>
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogUtil.println("**** " + this.getClass().getSimpleName() + "#doGet *****");

        HttpSession session = request.getSession();
        procSessionError(request, response, session);
        return;
    }

    /**
     * HTTPのGETメソッド受信時に呼び出される処理
     * <pre>
     * サブクラスでオーバライドする
     * </pre>
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogUtil.println("**** " + this.getClass().getSimpleName() + "#doPost *****");
    }

    /**
     * セッションを無効にして、セッションエラー画面に遷移する処理
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     */
    protected void procSessionError(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException,
    IOException {
        session.invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/login/session_error.jsp").forward(request, response);
    }
}
