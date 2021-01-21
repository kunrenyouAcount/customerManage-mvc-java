package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * デバッグログ出力のユーティリティクラス
 */
public class LogUtil {

    /**
     *  デバッグログ出力切り替えフラグ
     *  <pre>
     *  デバッグ情報出力する＝true
     *  デバッグ情報出力しない＝false
     *  </pre>
     */
    private static final boolean isDebug = true;

    /**
     * コンストラクタ(new演算子による生成禁止)
     */
    private LogUtil() {
    }

    /**
     * デバッグ情報を出力する
     * @param log デバッグ出力する文字列
     */
    public static void println(String log) {
        if (isDebug) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            System.out.println( dtf.format(now) + " " + log);
        }
    }

    /**
     * 例外発生時のスタックとレースを出力する
     * @param e 例外オブジェクト
     */
    public static void printStackTrace(Exception e) {
        if (isDebug) {
            e.printStackTrace();
        }
    }
}
