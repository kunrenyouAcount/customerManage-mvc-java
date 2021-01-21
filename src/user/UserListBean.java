package user;

import java.util.ArrayList;

/**
 * ユーザーリストBean
 */
public class UserListBean {

    /** ユーザーBeanのリスト */
    private ArrayList<UserBean> alUser = new ArrayList<UserBean>();

    /** ユーザーBeanのリストのインデックス */
    private int intCounter = 0;

    /**
     * ユーザーBeanのリストのゲッター
     * @return ユーザーBeanのリスト
     */
    public ArrayList<UserBean> getAlUser() {
        return alUser;
    }

    /**
     * ユーザーBeanのリストのセッター
     * @param alUser ユーザーBeanのリスト
     */
    public void setAlUser(ArrayList<UserBean> alUser) {
        this.alUser = alUser;
    }

    /**
     * ユーザーBeanのリストのインデックスのゲッター
     * @return ユーザーBeanのリストのインデックス
     */
    public int getIntCounter() {
        return intCounter;
    }

    /**
     * ユーザーBeanのリストのインデックスのセッター
     * @param intCounter ユーザーBeanのリストのインデックス
     */
    public void setIntCounter(int intCounter) {
        this.intCounter = intCounter;
    }

    /**
     * ユーザーBeanのリストの次の要素があるか確認する
     * @return 次の要素の有り(true)/無し(false)
     */
    public boolean hasNext() {
        boolean blResult = false;
        if (this.intCounter < this.alUser.size()) {
            blResult = true;
        }
        return blResult;
    }

    /**
     * ユーザーBeanのリストの次の要素を取得する
     * @return ユーザーBean
     */
    public UserBean getNext() {
        UserBean user = alUser.get(this.intCounter);
        this.intCounter++;
        return user;
    }
}
