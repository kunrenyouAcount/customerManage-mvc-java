package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.LogUtil;

/**
 * DAO(オートコミット)の基底クラス
 */
public class BaseDao {

	final String servername = "localhost";
	final String databasename = "customermanage";

	//データベース接続情報
	final String JDBC_URL = "jdbc:mysql://" + servername + "/" + databasename + "?serverTimeZone=JST";
	final String DB_USER = "customermanager";
	final String DB_PASS = "customer";

    /** DBへの接続 */
    protected Connection conn = null;

    /**
     * DBへ接続する
     *
     * @throws ClassNotFoundException JDBCドライバが存在しない
     * @throws SQLException DB接続失敗
     */
    protected void open() throws ClassNotFoundException, SQLException {
        LogUtil.println(this.getClass().getSimpleName() + "#open");

        conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
        conn.setAutoCommit(true);
    }

    /**
     * DBへの接続を終了する
     *
     * @throws SQLException DBへの接続終了失敗
     */
    protected void close() throws SQLException {
        LogUtil.println(this.getClass().getSimpleName() + "#close");

        if (conn != null) {
            conn.close();
            conn = null;
        }
    }
}
