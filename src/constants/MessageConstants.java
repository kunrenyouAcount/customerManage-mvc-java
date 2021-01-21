package constants;

/**
 * エラーメッセージ定義クラス
 */
public class MessageConstants {

    private MessageConstants() {
    }

    /** 既にログインしています。 */
    public static final String MESSAGE_ALREADY_LOGGED_IN = "既にログインしています。";

    /** 追加できませんでした。 */
    public static final String MESSAGE_CAN_NOT_ADD = "追加できませんでした。";

    /** 削除できませんでした。 */
    public static final String MESSAGE_CAN_NOT_DELETE = "削除できませんでした。";

    /** 更新できませんでした。 */
    public static final String MESSAGE_CAN_NOT_UPDATE = "更新できませんでした。";

    /** 一括処理は行われませんでした。 */
    public static final String MESSAGE_DID_NOT_BATCH_PROCESSING = "一括処理は行われませんでした。";

    /** 一括処理が失敗しました。 */
    public static final String MESSAGE_FAILED_BATCH_PROCESSING = "一括処理が失敗しました。";

    /** エクスポートに失敗しました。 */
    public static final String MESSAGE_FAILED_EXPORT = "エクスポートに失敗しました。";

    /** ファイルクローズに失敗しました。 */
    public static final String MESSAGE_FAILED_FILE_CLOSE = "ファイルクローズに失敗しました。";

    /** 古いパスワードが正しくありません。 */
    public static final String MESSAGE_INCORRECT_OLD_PASSWORD = "古いパスワードが正しくありません。";

    /** IDが不正です。 */
    public static final String MESSAGE_INVALID_ID = "IDが不正です。";

    /** 該当するデータがありません。 */
    public static final String MESSAGE_NO_EXIST_CORRESPOND_DATA = "該当するデータが存在しません。";

    /** 更新対象データが存在しません。 */
    public static final String MESSAGE_NO_EXIST_DATA_TO_UPDATE = "更新対象データが存在しません。";

    /** 削除対象データが存在しません。 */
    public static final String MESSAGE_NO_EXIST_DATA_TO_DELETE = "削除対象データが存在しません。";

    /** 操作権限がありません。 */
    public static final String MESSAGE_NO_OPERATION_PRIVILEGE = "操作権限がありません。";

    /** データが不足しています。 */
    public static final String MESSAGE_NOT_ENOUGH_DATA = "データが不足しています。";

    /** 旧パスワードと新パスワードが同一です。 */
    public static final String MESSAGE_SAME_OLD_PASSWORD_AND_NEW_PASSWORD = "旧パスワードと新パスワードが同一です。";
}
