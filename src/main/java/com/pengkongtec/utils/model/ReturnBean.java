package com.pengkongtec.utils.model;

public class ReturnBean {

	private String code;
	private String msg;
	private Data data;

	public ReturnBean() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ReturnBean [code=" + code + ", msg=" + msg + ", data="
				+ data + "]";
	}
}
