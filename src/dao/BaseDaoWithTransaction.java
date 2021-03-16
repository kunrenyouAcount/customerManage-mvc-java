package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.LogUtil;

/**
 * DAO(トランザクション制御あり)の基底クラス
 */
public class BaseDaoWithTransaction {

	final String servername = "localhost";
	final String databasename = "customermanage";

	//データベース接続情報
	final String JDBC_URL = "jdbc:mysql://" + servername + "/" + databasename + "?serverTimeZone=JST";
	final String DB_USER = "customermanager";
	final String DB_PASS = "customer";

    /** DBへの接続 */
    Connection conn = null;

    /**
     * DBへ接続し、トランザクションを開始する
     * @return エラーメッセージ(処理成功時、null)
     */
    public String startTransaction() {
        LogUtil.println(this.getClass().getSimpleName() + "#startTransaction");

        String errMessage = null;

        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        }
        return errMessage;
    }

    /**
     * トランザクションをコミットし、DBへの接続を終了する
     * @return エラーメッセージ(処理成功時、null)
     */
    public String endTransaction() {
        LogUtil.println(this.getClass().getSimpleName() + "#endTransaction");

        String errMessage = null;

        if (conn != null) {
            try {
                conn.commit();
                conn.setAutoCommit(true);
                conn.close();
                conn = null;
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }

        return errMessage;
    }

    /**
     * トランザクションをロールバックし、DBへの接続を終了する
     * @return エラーメッセージ(処理成功時、null)
     */
    public String cancelTransaction() {
        LogUtil.println(this.getClass().getSimpleName() + "#cancelTransaction");

        String errMessage = null;

        if (conn != null) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                conn.close();
                conn = null;
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }

        return errMessage;
    }
}