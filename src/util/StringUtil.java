package util;


/**
 * 文字列変換のユーティリティクラス
 */
public class StringUtil {

    /**
     * コンストラクタ(new演算子による生成禁止)
     */
    private StringUtil() {
    }

    /**
     * クロスサイトスクリプティング(XSS)対策処理
     * @param text 入力文字列
     * @return クロスサイトスクリプティング対策後の入力文字列
     */
    public static String exchangeESCEncoding(String text) {
        if (text == null) {
            return null;
        }
        text = text.replaceAll("&", "&amp;");
        text = text.replaceAll("<", "&lt;");
        text = text.replaceAll(">", "&gt;");
        text = text.replaceAll("\"", "&quot;");
        text = text.replaceAll("\'", "&apos;");
        return text;
    }
}
