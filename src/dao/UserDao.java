package dao;

import static constants.MessageConstants.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import user.UserBean;
import user.UserListBean;
import util.LogUtil;

/**
 * ユーザー管理DAO(オートコミット)
 */
public class UserDao extends BaseDao {

    /** DBMS_CRYPTOを使用した暗号化･復号化時のパスワード */
    private static final String SECRET = "SECPWD";

    /**
     * ログイン名とログインパスワードを指定してユーザーテーブルからユーザー情報を取得する
     * @param login ログイン名
     * @param pwd ログインパスワード
     * @return ユーザーの情報を格納したユーザーBean
     */
    public UserBean getUser(String login, String pwd) {
        LogUtil.println(this.getClass().getSimpleName() + "#getUser");

        PreparedStatement pstmt = null;
        UserBean user = null;
        String strSql = "SELECT ID, LOGIN, LVL, NAME, PWD_DECRYPT(PWD, '" +
                SECRET + "') FROM LOGIN_USER WHERE LOGIN=? AND PWD=PWD_ENCRYPT(?, '" + SECRET + "')";
        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setString(1, login);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLvl(rs.getInt("lvl"));
                rs.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                LogUtil.printStackTrace(e);
            }
        }

        return user;
    }

    /**
     * ユーザーテーブルの指定ユーザーのログインパスワードを更新する
     * @param newPwd 新しいログインパスワード
     * @param id ユーザーテーブルのID
     * @param oldPwd 既存のログインパスワード
     * @return エラーメッセージ(処理成功時、null)
     */
    public String updateUser(String newPwd, int id, String oldPwd) {
        LogUtil.println(this.getClass().getSimpleName() + "#updateUser");

        String errMessage = null;
        PreparedStatement pstmt = null;
        String strSql = "UPDATE LOGIN_USER SET pwd=PWD_ENCRYPT(?,'" + SECRET + "')"
                + " WHERE id=? AND pwd=PWD_ENCRYPT(?, '" + SECRET + "')";
        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setString(1, newPwd);
            pstmt.setInt(2, id);
            pstmt.setString(3, oldPwd);
            int intResult = pstmt.executeUpdate();
            if (intResult != 1) {
                errMessage = MESSAGE_INCORRECT_OLD_PASSWORD;
            }
            return errMessage;
        } catch (ClassNotFoundException | SQLException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }
        return errMessage;
    }

    /**
     * ユーザーテーブルへ指定ユーザーの情報を追加する
     * @param pwd ログインパスワード
     * @param user ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String addUser(String pwd, UserBean user) {
        LogUtil.println(this.getClass().getSimpleName() + "#addUser");

        String errMessage = null;
        PreparedStatement pstmt = null;
        String strSql = "INSERT INTO LOGIN_USER (id,name,login,lvl,pwd)"
                + " VALUES(sequence_login_user_id.NEXTVAL,?,?,?,PWD_ENCRYPT(?, '" + SECRET + "'))";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLogin());
            pstmt.setInt(3, user.getLvl());
            pstmt.setString(4, pwd);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }

        return errMessage;
    }

    /**
     * IDを指定してユーザーテーブルからユーザー情報を取得する
     * @param id ユーザーテーブルのID
     * @return ユーザーBean
     */
    public UserBean load(int id) {
        LogUtil.println(this.getClass().getSimpleName() + "#load");

        PreparedStatement pstmt = null;
        String strSql = "SELECT * FROM LOGIN_USER WHERE id =?";
        UserBean user = null;

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setLvl(rs.getInt("lvl"));
                rs.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                LogUtil.printStackTrace(e);
            }
        }

        return user;
    }

    /**
     * ユーザーテーブルの指定ユーザーの情報を更新する
     * @param pwd  ログインパスワード(変更しない場合、null)
     * @param user ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String update(String pwd, UserBean user) {
        LogUtil.println(this.getClass().getSimpleName() + "#update");

        String errMessage = null;
        PreparedStatement pstmt = null;

        try {
            open();
            String strSql = null;
            if (pwd == null) {
                strSql = "UPDATE LOGIN_USER set login=?,name=?,lvl=? WHERE id=?";
                pstmt = conn.prepareStatement(strSql);
                pstmt.setString(1, user.getLogin());
                pstmt.setString(2, user.getName());
                pstmt.setInt(3, user.getLvl());
                pstmt.setInt(4, user.getId());
            } else {
                strSql = "UPDATE LOGIN_USER set login=?,name=?,lvl=?,pwd=PWD_ENCRYPT(?,'" + SECRET + "')"
                        + " WHERE id=?";
                pstmt = conn.prepareStatement(strSql);
                pstmt.setString(1, user.getLogin());
                pstmt.setString(2, user.getName());
                pstmt.setInt(3, user.getLvl());
                pstmt.setString(4, pwd);
                pstmt.setInt(5, user.getId());
            }

            int intResult = pstmt.executeUpdate();
            if (intResult != 1) {
                errMessage = MESSAGE_NO_EXIST_CORRESPOND_DATA;
            }
        } catch (SQLException | ClassNotFoundException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }
        return errMessage;
    }

    /**
     * IDを指定してユーザーテーブルからユーザー情報を削除する
     * @param id ユーザーテーブルのID
     * @return エラーメッセージ(処理成功時、null)
     */
    public String delete(int id) {
        LogUtil.println(this.getClass().getSimpleName() + "#delete");

        String errMessage = null;
        PreparedStatement pstmt = null;
        String strSql = "UPDATE LOGIN_USER set login=null,lvl=-1,pwd=null WHERE id=?";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setInt(1, id);

            int intResult = pstmt.executeUpdate();
            if (intResult != 1) {
                errMessage = MESSAGE_CAN_NOT_DELETE;
            }
        } catch (SQLException | ClassNotFoundException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }
        return errMessage;
    }

    /**
     * ユーザーテーブルから全ユーザーの情報を取得する
     * @return ユーザーBeanのリスト
     */
    public UserListBean loadAll() {
        LogUtil.println(this.getClass().getSimpleName() + "#loadAll");

        PreparedStatement pstmt = null;
        ArrayList<UserBean> alUser = new ArrayList<UserBean>();
        String strSql = "SELECT * FROM LOGIN_USER WHERE lvl>=0 ORDER BY id ASC";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            ResultSet rs = pstmt.executeQuery();
            alUser.clear();
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setLvl(rs.getInt("lvl"));
                alUser.add(user);
            }
            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                LogUtil.printStackTrace(e);
            }
        }

        UserListBean userListBean = new UserListBean();
        userListBean.setAlUser(alUser);
        userListBean.setIntCounter(0);

        return userListBean;
    }
}
