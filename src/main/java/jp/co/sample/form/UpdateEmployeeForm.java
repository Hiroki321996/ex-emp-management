package jp.co.sample.form;

/**
 * 従業員情報のリクエストパラメータを受け取るクラス.
 * 
 * @author sanihiro
 *
 */
public class UpdateEmployeeForm {

	/**
	 *  ID
	 */
	private String id;
	
	/**
	 *  名前
	 */
	private String name;
	
	/**
	 * 画像
	 */
	private String image;
	
	/**
	 * 性別
	 */
	private String gender;
	
	/**
	 * 入社日
	 */
	private String hireDate;
	
	/**
	 * メールアドレス
	 */
	private String meilAddress;
	
	/**
	 * 郵便番号
	 */
	private String zipCode;
	
	/**
	 * 住所
	 */
	private String address;
	
	/**
	 * 電話番号
	 */
	private String telephone;
	
	/**
	 * 給料
	 */
	private String salary;
	
	/**
	 * 特性
	 */
	private String characteristics;
	
	/**
	 *  扶養人数
	 */
	private String dependentsCount;
	
	/**
	 * idをint型に変換.
	 * 
	 * @return int型にしたageプロパティ
	 */
	public int getIntId() {
		return Integer.parseInt(id);
	}
	
	/**
	 * dependentsCountをint型に変換.
	 * 
	 * @return int型にしたdependentsCount
	 */
	public int getIntDependentsCount() {
		return Integer.parseInt(dependentsCount);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}

}
