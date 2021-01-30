package customer;

/**
 * 顧客情報Bean
 */
public class CustomerBean {
    /** ID */
    private int intId;
    /** 氏名 */
    private String strName;
    /** 郵便番号 */
    private String strZip;
    /** 住所1 */
    private String strAddress1;
    /** 住所2 */
    private String strAddress2;
    /** 電話番号 */
    private String strTel;
    /** FAX番号 */
    private String strFax;
    /** E-mailアドレス */
    private String strEmail;

    public CustomerBean() {};

    public CustomerBean(String strName, String strZip, String strAddress1, String strAddress2, String strTel, String strFax, String strEmail) {
        this.strName = strName;
        this.strZip = strZip;
        this.strAddress1 = strAddress1;
        this.strAddress2 = strAddress2;
        this.strTel = strTel;
        this.strFax = strFax;
        this.strEmail = strEmail;
    }
    /**
     * IDのセッター
     * @param id ID
     */
    public void setId(int id) {
        this.intId = id;
    }

    /**
     * 氏名のセッター
     * @param name 氏名
     */
    public void setName(String name) {
        this.strName = name;
    }

    /**
     * 郵便番号のセッター
     * @param zip 郵便番号
     */
    public void setZip(String zip) {
        this.strZip = zip;
    }

    /**
     * 住所1のセッター
     * @param address1 住所1
     */
    public void setAddress1(String address1) {
        this.strAddress1 = address1;
    }

    /**
     * 住所2のセッター
     * @param address2 住所2
     */
    public void setAddress2(String address2) {
        this.strAddress2 = address2;
    }

    /**
     * 電話番号のセッター
     * @param tel 電話番号
     */
    public void setTel(String tel) {
        this.strTel = tel;
    }

    /**
     * FAX番号のセッター
     * @param fax FAX番号
     */
    public void setFax(String fax) {
        this.strFax = fax;
    }

    /**
     * E-mailアドレスのセッター
     * @param email E-mailアドレス
     */
    public void setEmail(String email) {
        this.strEmail = email;
    }

    /**
     * IDのゲッター
     * @return ID
     */
    public int getId() {
        return this.intId;
    }

    /**
     * 氏名のゲッター
     * @return 氏名
     */
    public String getName() {
        return this.strName;
    }

    /**
     * 郵便番号のゲッター
     * @return 郵便番号
     */
    public String getZip() {
        return this.strZip;
    }

    /**
     * 住所1のゲッター
     * @return 住所1
     */
    public String getAddress1() {
        return this.strAddress1;
    }

    /**
     * 住所2のゲッター
     * @return 住所2
     */
    public String getAddress2() {
        return this.strAddress2;
    }

    /**
     * 電話番号のゲッター
     * @return 電話番号
     */
    public String getTel() {
        return this.strTel;
    }

    /**
     * FAX番号のゲッター
     * @return FAX番号
     */
    public String getFax() {
        return this.strFax;
    }

    /**
     * E-mailアドレスのゲッター
     * @return E-mailアドレス
     */
    public String getEmail() {
        return this.strEmail;
    }
}