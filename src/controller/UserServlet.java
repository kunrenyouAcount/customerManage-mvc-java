package controller;

import static constants.MessageConstants.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserBean;
import user.UserListBean;
import user.UserListLogic;
import user.UserLogic;
import util.LogUtil;
import util.StringUtil;

/**
 * ユーザー管理のサーブレット
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @see BaseServlet#BaseServlet()
     */
    public UserServlet() {
        super();
    }

    /**
     * HTTPのPOSTメソッド受信時に呼び出される処理
     * <pre>
     * セッションに含まれるstate属性の値に応じて
     * ユーザー管理固有の処理を行う
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

        String param = request.getParameter("state");
        String[] state = param.split(",");

        LogUtil.println("state: " + state[0]);

        if (state[0] == null) {
            return;
        }

        switch (state[0]) {
        case "list":
            procList(request, response, session, user);
            break;
        case "new":
            procNew(request, response, session, user);
            break;
        case "edit":
            procEdit(request, response, session, user, state[1]);
            break;
        case "delete":
            procDelete(request, response, session, user);
            break;
        case "update":
            procUpdate(request, response, session, user);
            break;
        case "add":
            procAdd(request, response, session, user);
            break;
        }
    }

    /**
     * ユーザ情報の全レコードをDBから取得し、ユーザー一覧画面に遷移する
     * <pre>
     * state属性＝"list"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procList(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserBean user) throws ServletException,
    IOException {
        if (user.getLvl() != 2) {
            session.setAttribute("errMessage", MESSAGE_NO_OPERATION_PRIVILEGE);
            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
            return;
        }

        UserListLogic userListLogic = new UserListLogic();
        UserListBean userListBean = null;
        userListBean = userListLogic.load();

        request.setAttribute("userList", userListBean);
        getServletContext().getRequestDispatcher("/WEB-INF/user/list.jsp").forward(request, response);
    }

    /**
     * 新規登録画面に遷移する
     * <pre>
     * state属性＝"new"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procNew(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserBean user)
            throws ServletException, IOException {
        if (user.getLvl() != 2) {
            session.setAttribute("errMessage", MESSAGE_NO_OPERATION_PRIVILEGE);
            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/user/new.jsp").forward(request, response);
    }

    /**
     * 修正対象となるユーザー情報をDBから取得し、既存データ編集･削除画面に遷移する
     * <pre>
     * state属性＝"edit"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     * @param id        修正対象となるユーザー情報のID
     */
    private void procEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserBean user, String id)
            throws ServletException, IOException {
        if (user.getLvl() != 2) {
            session.setAttribute("errMessage", MESSAGE_NO_OPERATION_PRIVILEGE);
            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
            return;
        }

        int intUserId = Integer.parseInt(id);
        UserLogic userLogic = new UserLogic();
        UserBean userEdit = null;
        String errMessage = null;
        userEdit = userLogic.load(intUserId);

        if (userEdit == null) {
            session.setAttribute("errMessage", errMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
        }
        session.setAttribute("userEdit", userEdit);
        getServletContext().getRequestDispatcher("/WEB-INF/user/edit.jsp").forward(request, response);
    }

    /**
     * 削除対象のユーザ情報をセッションから取得し、DB削除処理後、削除完了(成功時)画面、または、削除未完了(失敗時)画面に遷移する
     * <pre>
     * state属性＝"delete"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserBean user)
            throws ServletException, IOException {
        if (user.getLvl() != 2) {
            session.setAttribute("errMessage", MESSAGE_NO_OPERATION_PRIVILEGE);
            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
            return;
        }
        String errMessage = null;
        UserBean userEdit = (UserBean) session.getAttribute("userEdit");
        UserLogic userLogic = new UserLogic();
        errMessage = userLogic.delete(userEdit);

        session.removeAttribute("userEdit");

        if (errMessage == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/user/delete_success.jsp").forward(request, response);
        } else {
            session.setAttribute("errMessage", errMessage);

            getServletContext().getRequestDispatcher("/WEB-INF/user/delete_fail.jsp").forward(request, response);
        }
    }

    /**
     * 更新対象のユーザー情報をリクエストから取得し、DB更新処理後、更新完了(成功時)画面、または、更新未完了(失敗時)画面に遷移する
     * <pre>
     * state属性＝"update"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserBean user)
            throws ServletException, IOException, UnsupportedEncodingException {
        if (user.getLvl() != 2) {
            session.setAttribute("errMessage", MESSAGE_NO_OPERATION_PRIVILEGE);
            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
            return;
        }
        String strPassword = StringUtil.exchangeESCEncoding(request.getParameter("password1"));
        UserLogic userLogic = new UserLogic();
        UserBean userEdit = userLogic.createUserBeanFromRequest(request);
        String errMessage = null;
        errMessage = userLogic.update(strPassword, userEdit);

        session.removeAttribute("userEdit");

        if (errMessage == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/user/update_success.jsp").forward(request, response);
        } else {
            session.setAttribute("errMessage", errMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/user/update_fail.jsp").forward(request, response);
        }
    }

    /**
     * 追加対象のユーザー情報をリクエストから取得し、DB追加処理後、新規登録完了(成功時)画面、または、新規登録未完了(失敗時)画面に遷移する
     * <pre>
     * state属性＝"add"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procAdd(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserBean user)
            throws ServletException, IOException, UnsupportedEncodingException {
        if (user.getLvl() != 2) {
            session.setAttribute("errMessage", MESSAGE_NO_OPERATION_PRIVILEGE);
            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
            return;
        }

        String strPassword = StringUtil.exchangeESCEncoding(request.getParameter("password1"));
        UserLogic userLogic = new UserLogic();
        UserBean userEdit = userLogic.createUserBeanFromRequest(request);
        String errMessage = null;
        errMessage = userLogic.add(strPassword, userEdit);

        session.removeAttribute("userEdit");

        if (errMessage == null) {
            session.setAttribute("userEdit", userEdit);
            getServletContext().getRequestDispatcher("/WEB-INF/user/add_success.jsp").forward(request, response);
        } else {
            session.setAttribute("errMessage", errMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/user/add_fail.jsp").forward(request, response);
        }
    }
}
