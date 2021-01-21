package customer;

import java.util.ArrayList;

/**
 * 顧客情報リストBean
 */
public class CustomerListBean {

    /** 顧客情報Beanのリスト */
    private ArrayList<CustomerBean> alCustomer;

    /** 顧客情報Beanのリストのインデックス */
    private int intCounter = 0;

    /**
     * 顧客情報Beanのリストのセッター
     * @param alCustomer 顧客情報Beanのリスト
     */
    public void setAlCustomer(ArrayList<CustomerBean> alCustomer) {
        this.alCustomer = alCustomer;
    }

    /**
     * 顧客情報Beanのリストの次の要素があるか確認する
     * @return 次の要素の有り(true)/無し(false)
     */
    public boolean hasNext() {
        boolean blResult = false;
        if (this.intCounter < this.alCustomer.size()) {
            blResult = true;
        }
        return blResult;
    }

    /**
     * 顧客情報Beanのリストの次の要素を取得する
     * @return 顧客情報Bean
     */
    public CustomerBean getNext() {
        CustomerBean customer = alCustomer.get(this.intCounter);
        this.intCounter++;
        return customer;
    }
}
