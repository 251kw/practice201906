package dto;

// 書き込み内容を保持するクラス
public class ShoutDTO {
	private String shoutsId;
	private String userName;	// ユーザ名
	private String icon;		// ユーザアイコン
	private String date;		// 書き込み日時
	private String writing;		// 書き込み内容
	private String loginId;

	public ShoutDTO() {

	}

	public ShoutDTO(String shoutsId,String loginId,String userName, String icon, String date, String writing) {
		this.setShoutsId(shoutsId);
		this.loginId = loginId;
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
	}

	// 各メンバ変数の getter および setter
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getShoutsId() {
		return shoutsId;
	}

	public void setShoutsId(String shoutsId) {
		this.shoutsId = shoutsId;
	}
}
