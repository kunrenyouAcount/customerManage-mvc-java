package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserBean;
import user.UserLogic;
import util.LogUtil;
import customer.CustomerCsvLogic;

/**
 * 顧客管理（CSV）のサーブレット
 */
/**
 * @author JspServletOracle
 *
 */
@WebServlet("/CustomerCsvServlet")
@MultipartConfig
public class CustomerCsvServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @see BaseServlet#BaseServlet()
     */
    public CustomerCsvServlet() {
        super();
    }

    /**
     * HTTPのPOSTメソッド受信時に呼び出される処理
     * <pre>
     * セッションに含まれるstate属性の値に応じて
     * 顧客管理（CSV）固有の処理を行う
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
        case "customer_csv":
            procCustomerCsv(request, response);
            break;
        case "read":
            procRead(request, response, session);
            break;
        case "write":
            procWrite(request, response, session);
            break;
        }
    }

    /**
     * 顧客管理（CSV）画面に遷移する
     * state属性＝"customer_csv"時の処理
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     */
    private void procCustomerCsv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/csv/customer_csv.jsp").forward(request, response);
    }

    /**
     * CSVファイルから一括処理データを読み込み、顧客情報テーブルに対して一括処理を行う
     * state属性＝"read"時の処理
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     */
    private void procRead(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        CustomerCsvLogic csv = new CustomerCsvLogic();
        String errMessage = null;
        errMessage = csv.read(request);

        if (errMessage == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/csv/read_success.jsp").forward(request, response);
        } else {
            session.setAttribute("errMessage", errMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/csv/read_fail.jsp").forward(request, response);
        }
    }

    /**
     * 顧客情報テーブルの全レコードを、CSVファイルに書き出す
     * state属性＝"write"時の処理
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     */
    private void procWrite(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        CustomerCsvLogic csv = new CustomerCsvLogic();
        String errMessage = null;
        errMessage = csv.write(response);

        if (errMessage != null) {
            LogUtil.println(errMessage);
        }
    }
}
