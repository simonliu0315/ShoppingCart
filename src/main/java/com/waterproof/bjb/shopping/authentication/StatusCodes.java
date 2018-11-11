package com.waterproof.bjb.shopping.authentication;

public enum StatusCodes {

	DATABASE(0, "", "A database error has occured."), 
	DUPLICATE_USER(1, "", "This user already exists."),
	BADCAPTCHA(2, "WF001E", "驗證碼錯誤"),
	CREDENTIALS(3, "WF002E", "帳號或密碼錯誤");

	private final int id;
	private final String code;
	private final String description;

	private StatusCodes(int id, String code, String description) {
		this.id = id;
	    this.code = code;
	    this.description = description;
	  }

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return id + ":" + code + ": " + description;
	}
}