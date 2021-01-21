package dao;

import static constants.MessageConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.LogUtil;
import customer.CustomerBean;

/**
 * 顧客管理DAO(トランザクション制御あり)
 */
public class CustomerDaoWithTransaction extends BaseDaoWithTransaction {

    /**
     * 顧客情報テーブルへ指定の顧客情報を追加する。
     * 追加する顧客情報のIDは、シーケンスの｢sequence_customer_id.NEXTVAL｣によって自動採番し、
     * CSVファイルに指定されたIDの値は無視する。
     * @param customer 顧客情報Bean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String add(CustomerBean customer) {
        LogUtil.println(this.getClass().getSimpleName() + "#add");

        String errMessage = null;
        PreparedStatement pstmt = null;
        String strSql = "INSERT INTO customer (id,name,zip,address1,address2,tel,fax,email)"
                + " VALUES(sequence_customer_id.NEXTVAL,?,?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(strSql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getZip());
            pstmt.setString(3, customer.getAddress1());
            pstmt.setString(4, customer.getAddress2());
            pstmt.setString(5, customer.getTel());
            pstmt.setString(6, customer.getFax());
            pstmt.setString(7, customer.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }
        return errMessage;
    }

    /**
     * 顧客情報テーブルの指定の顧客情報を更新する
     * @param cutomer 顧客情報Bean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String update(CustomerBean cutomer) {
        LogUtil.println(this.getClass().getSimpleName() + "#update");

        String errMessage = null;
        PreparedStatement pstmt = null;
        String strSql = "UPDATE CUSTOMER SET id=?,name=?,zip=?,address1=?,address2=?,"
                + "tel=?,fax=?,email=? WHERE id=?";

        try {
            pstmt = conn.prepareStatement(strSql);
            pstmt.setInt(1, cutomer.getId());
            pstmt.setString(2, cutomer.getName());
            pstmt.setString(3, cutomer.getZip());
            pstmt.setString(4, cutomer.getAddress1());
            pstmt.setString(5, cutomer.getAddress2());
            pstmt.setString(6, cutomer.getTel());
            pstmt.setString(7, cutomer.getFax());
            pstmt.setString(8, cutomer.getEmail());
            pstmt.setInt(9, cutomer.getId());

            int result = pstmt.executeUpdate();
            if (result == 0) {
                errMessage = MESSAGE_NO_EXIST_DATA_TO_UPDATE;
            }
        } catch (SQLException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }
        return errMessage;
    }

    /**
     * IDを指定して顧客情報テーブルから顧客情報を削除する
     * @param id 顧客情報テーブルのID
     * @return エラーメッセージ(処理成功時、null)
     */
    public String delete(int id) {
        LogUtil.println(this.getClass().getSimpleName() + "#delete");

        String errMessage = null;
        PreparedStatement pstmt = null;
        String strSql = "DELETE from CUSTOMER WHERE id=?";

        try {
            pstmt = conn.prepareStatement(strSql);
            pstmt.setInt(1, id);

            int result = pstmt.executeUpdate();
            if (result == 0) {
                errMessage = MESSAGE_NO_EXIST_DATA_TO_DELETE;
            }
        } catch (SQLException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }
        return errMessage;
    }
}