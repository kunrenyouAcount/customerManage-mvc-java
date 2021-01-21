package user;

import static constants.MessageConstants.*;

import javax.servlet.http.HttpServletRequest;

import util.LogUtil;
import util.StringUtil;
import dao.UserDao;

/**
 * ユーザーのロジック
 */
public class UserLogic {

    /**
     * 指定のログイン名とログインパスワードでログイン可能かチェックする
     * @param login ログイン名
     * @param pwd ログインパスワード
     * @return ユーザーBean(ログイン不可の場合、null)
     */
    public UserBean login(String login, String pwd) {
        LogUtil.println(this.getClass().getSimpleName() + "#login");

        UserDao userDao = new UserDao();
        UserBean user = userDao.getUser(login, pwd);

        return user;
    }

    /**
     * 指定ユーザーのパスワードを変更する
     * @param oldPwd 既存ログインパスワード
     * @param intId ユーザーテーブルのID
     * @param newPwd 新ログインパスワード
     * @return エラーメッセージ(処理成功時、null)
     */
    public String passwordChange(String oldPwd, int intId, String newPwd) {
        LogUtil.println(this.getClass().getSimpleName() + "#passwordChange");

        String errMessage = null;

        if (oldPwd.equals(newPwd)) {
            errMessage = MESSAGE_SAME_OLD_PASSWORD_AND_NEW_PASSWORD;
        } else {
            UserDao userDao = new UserDao();
            errMessage = userDao.updateUser(newPwd, intId, oldPwd);
        }

        return errMessage;
    }

    /**
     * 指定ユーザーを追加する
     * @param pwd ログインパスワード
     * @param user ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String add(String pwd, UserBean user) {
        LogUtil.println(this.getClass().getSimpleName() + "#add");

        String errMessage = null;
        UserDao userDao = new UserDao();
        if (userDao.addUser(pwd, user) != null) {
            errMessage = MESSAGE_CAN_NOT_ADD;
        }

        return errMessage;
    }

    /**
     * IDで指定されたユーザーを取得する
     * @param id ユーザーテーブルのID
     * @return ユーザーBean
     */
    public UserBean load(int id) {
        LogUtil.println(this.getClass().getSimpleName() + "#load");

        UserDao userDao = new UserDao();
        UserBean user = userDao.load(id);

        return user;
    }

    /**
     * ユーザーBeanの情報でユーザーを更新する
     * @param pwd 新ログインパスワード
     * @param user ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String update(String pwd, UserBean user) {
        LogUtil.println(this.getClass().getSimpleName() + "#update");

        if (user == null) {
            return MESSAGE_NO_EXIST_CORRESPOND_DATA;
        }

        String errMessage = null;
        UserDao userDao = new UserDao();
        errMessage = userDao.update(pwd, user);
        if (errMessage != null) {
            errMessage = MESSAGE_CAN_NOT_UPDATE;
        }
        return errMessage;
    }

    /**
     * ユーザーBeanで指定されたユーザーを削除する
     * @param user ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String delete(UserBean user) {
        LogUtil.println(this.getClass().getSimpleName() + "#delete");

        if (user == null) {
            return MESSAGE_NO_EXIST_CORRESPOND_DATA;
        }

        String errMessage = null;
        UserDao userDao = new UserDao();
        errMessage = userDao.delete(user.getId());
        if (errMessage != null) {
            errMessage = MESSAGE_CAN_NOT_DELETE;
        }

        return errMessage;
    }

    /**
     * HTTPリクエスト内の情報からユーザーBeanを生成する
     * @param request HTTPのリクエスト
     * @return ユーザーBean
     */
    public UserBean createUserBeanFromRequest(HttpServletRequest request) {
        LogUtil.println(this.getClass().getSimpleName() + "#createUserBeanFromRequest");

        UserBean userEdit = (UserBean)request.getSession().getAttribute("userEdit");
        if (userEdit == null) {
            userEdit = new UserBean();
        }

        userEdit.setLogin(StringUtil.exchangeESCEncoding(request.getParameter("login")));
        userEdit.setName(StringUtil.exchangeESCEncoding(request.getParameter("user_name")));
        userEdit.setLvl(Integer.parseInt(request.getParameter("user_level")));
        return userEdit;
    }
}
