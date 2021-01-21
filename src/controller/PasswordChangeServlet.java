package controller;

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
 * ログインパスワード変更のサーブレット
 */
@WebServlet("/PasswordChangeServlet")
public class PasswordChangeServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @see BaseServlet#BaseServlet()
     */
    public PasswordChangeServlet() {
        super();
    }

    /**
     * HTTPのPOSTメソッド受信時に呼び出される処理
     * <pre>
     * セッションに含まれるstate属性の値に応じて
     * ログインパスワード変更固有の処理を行う
     * </pre>
     * @see BaseServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogUtil.println("**** " + this.getClass().getSimpleName() + "#doPost *****");

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute("user");

        if((user == null) || (user.getName() == null)){
            procSessionError(request, response, session);
            return;
        }

        UserLogic userLogic = new UserLogic();
        user = userLogic.load(user.getId());
        session.setAttribute("user", user);

        String state = request.getParameter("state");
        LogUtil.println("state: " + state);

        if (state == null) {
            procSessionError(request, response, session);
            return;
        }

        switch (state) {
        case "password_change_form":
            procPasswordChangeForm(request, response);
            break;
        case "password_change_process":
            procPasswordChangeProcess(request, response, session, user);
            break;
        }
    }

    /**
     * パスワード変更画面に遷移する
     * <pre>
     * state属性＝"password_change_form"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     */
    private void procPasswordChangeForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login/password_change_form.jsp").forward(request, response);
    }

    /**
     * リクエスト内の旧･新パスワードで、ログイン中ユーザ情報をDB更新処理後、パスワード変更完了(成功時)画面、パスワード変更未完了(失敗時)画面に遷移する
     * <pre>
     * state属性＝"password_change_process"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procPasswordChangeProcess(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, UserBean user) throws ServletException, IOException {
        String strOldPassword = StringUtil.exchangeESCEncoding(request.getParameter("old_password"));
        String strNewPassword = StringUtil.exchangeESCEncoding(request.getParameter("new_password1"));

        UserLogic userLogic = new UserLogic();
        String errMessage = userLogic.passwordChange(strOldPassword, user.getId(), strNewPassword);

        if(errMessage == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/login/password_change_success.jsp").forward(request, response);
        }else{
            session.setAttribute("errMessage", errMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/login/password_change_error.jsp").forward(request, response);
        }
    }
}
