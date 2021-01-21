package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.LogUtil;
import customer.CustomerBean;

/**
 * 顧客管理DAO(オートコミット)
 *
 */
public class CustomerDao extends BaseDao {

    /**
     * 顧客情報テーブルから全顧客の情報を取得する
     * @return 顧客情報Beanのリスト
     */
    public ArrayList<CustomerBean> loadAll() {
        LogUtil.println(this.getClass().getSimpleName() + "#loadAll");

        PreparedStatement pstmt = null;
        ArrayList<CustomerBean> alCustomer = new ArrayList<CustomerBean>();
        String strSql = "SELECT * FROM CUSTOMER ORDER BY id ASC";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CustomerBean customer = new CustomerBean();
                customer.setId(rs.getInt("id"));
                customer.setZip(rs.getString("zip"));
                customer.setName(rs.getString("name"));
                customer.setAddress1(rs.getString("address1"));
                customer.setAddress2(rs.getString("address2"));
                customer.setTel(rs.getString("tel"));
                customer.setFax(rs.getString("fax"));
                customer.setEmail(rs.getString("email"));
                alCustomer.add(customer);
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

        return alCustomer;
    }

    /**
     * 顧客情報テーブルから氏名と検索キーワードが一致する顧客の情報を取得する
     * @param name 顧客情報テーブルを氏名で部分一致検索するためのキーワード
     * @return 顧客情報Beanのリスト
     */
    public ArrayList<CustomerBean> searchByName(String name) {
        LogUtil.println(this.getClass().getSimpleName() + "#searchByName");

        PreparedStatement pstmt = null;
        ArrayList<CustomerBean> alCustomer = new ArrayList<CustomerBean>();;
        alCustomer.clear();
        String strSql = "SELECT * FROM CUSTOMER WHERE name LIKE ? ORDER BY id ASC";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CustomerBean customer = new CustomerBean();
                customer.setId(rs.getInt("id"));
                customer.setZip(rs.getString("zip"));
                customer.setName(rs.getString("name"));
                customer.setAddress1(rs.getString("address1"));
                customer.setAddress2(rs.getString("address2"));
                alCustomer.add(customer);
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

        return alCustomer;
    }

    /**
     * IDを指定して顧客情報テーブルから顧客情報を取得する
     * @param id 顧客情報テーブルのID
     * @return 顧客情報Bean
     */
    public CustomerBean load(int id) {
        LogUtil.println(this.getClass().getSimpleName() + "#load");

        PreparedStatement pstmt = null;
        String strSql = "SELECT * FROM CUSTOMER WHERE id =?";
        CustomerBean customer = null;

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new CustomerBean();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setZip(rs.getString("zip"));
                customer.setAddress1(rs.getString("address1"));
                customer.setAddress2(rs.getString("address2"));
                customer.setTel(rs.getString("tel"));
                customer.setFax(rs.getString("fax"));
                customer.setEmail(rs.getString("email"));
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

        return customer;
    }

    /**
     * 顧客情報テーブルへ指定の顧客情報を追加する。
     * @param customer 顧客情報Bean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String add(CustomerBean customer) {
        LogUtil.println(this.getClass().getSimpleName() + "#add");

        // TODO 未実装

        return null;
    }

    /**
     * 顧客情報テーブルの指定の顧客情報を更新する
     * @param cutomer 顧客情報Bean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String update(CustomerBean cutomer) {
        LogUtil.println(this.getClass().getSimpleName() + "#update");

        // TODO 未実装

        return null;
    }

    /**
     * IDを指定して顧客情報テーブルから顧客情報を削除する
     * @param id 顧客情報テーブルのID
     * @return エラーメッセージ(処理成功時、null)
     */
    public String delete(int id) {
        LogUtil.println(this.getClass().getSimpleName() + "#delete");

        // TODO 未実装

        return null;
    }
}