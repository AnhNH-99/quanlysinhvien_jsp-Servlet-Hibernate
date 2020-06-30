package com.javaweb.model;

public class ChuyenNganh {
	private int machuyennganh;
	private String tenchuyennganh;
	private Khoa khoa;
	public ChuyenNganh() {
		super();
	}
	public ChuyenNganh(String tenchuyennganh, Khoa khoa) {
		super();
		this.tenchuyennganh = tenchuyennganh;
		this.khoa = khoa;
	}
	public int getMachuyennganh() {
		return machuyennganh;
	}
	public void setMachuyennganh(int machuyennganh) {
		this.machuyennganh = machuyennganh;
	}
	public String getTenchuyennganh() {
		return tenchuyennganh;
	}
	public void setTenchuyennganh(String tenchuyennganh) {
		this.tenchuyennganh = tenchuyennganh;
	}
	public Khoa getKhoa() {
		return khoa;
	}
	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}
	
}
