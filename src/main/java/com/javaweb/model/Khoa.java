package com.javaweb.model;

public class Khoa {
	private int makhoa;
	private String tenkhoa;
	private String sodienthoai;
	public Khoa() {
		super();
	}
	public Khoa( String tenkhoa, String sodienthoai) {
		super();
		this.tenkhoa = tenkhoa;
		this.sodienthoai = sodienthoai;
	}
	public int getMakhoa() {
		return makhoa;
	}
	public void setMakhoa(int makhoa) {
		this.makhoa = makhoa;
	}
	public String getTenkhoa() {
		return tenkhoa;
	}
	public void setTenkhoa(String tenkhoa) {
		this.tenkhoa = tenkhoa;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	
}
