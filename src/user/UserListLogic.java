package user;

import util.LogUtil;
import dao.UserDao;

/**
 * ユーザーリストのロジック
 *
 */
public class UserListLogic {

    /**
     * DBから全ユーザーのユーザーBeanのリストを取得する
     * @return 全ユーザーのユーザーBeanのリスト
     */
    public UserListBean load() {
        LogUtil.println(this.getClass().getSimpleName() + "#load");

        UserDao userDao = new UserDao();
        UserListBean userListBean = userDao.loadAll();

        return userListBean;
    }
}
