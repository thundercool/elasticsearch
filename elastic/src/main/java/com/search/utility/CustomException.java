package com.search.utility;

public enum CustomException {
	
	DATA_NOT_PRESENT("Data Not Present"),
	INVALID_EPISODE_ID_EXCEPTION("Invalid Episode Id");
	
	private final String code;
	
	private CustomException(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
