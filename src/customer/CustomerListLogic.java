package customer;

import java.util.ArrayList;

import util.LogUtil;
import dao.CustomerDao;

/**
 * 顧客情報リストのロジック
 */
public class CustomerListLogic {

    /**
     * DBから顧客情報を氏名で検索し顧客情報Beanのリストを取得する
     * @param search 顧客情報を氏名で検索する際の文字列
     * @return 検索結果の顧客情報Beanのリスト
     */
    public ArrayList<CustomerBean> search(String search) {
        LogUtil.println(this.getClass().getSimpleName() + "#search");

        CustomerDao customerDao = new CustomerDao();
        ArrayList<CustomerBean> alCustomer = new ArrayList<CustomerBean>();
        alCustomer = customerDao.searchByName(search);
        return alCustomer;
    }
}
