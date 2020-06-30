package com.javaweb.model;

public class TaiKhoan {
	private String tendangnhap;
	private String matkhau;
	private String hote;
	public TaiKhoan() {
		super();
	}
	public TaiKhoan(String tendangnhap, String matkhau, String hote) {
		super();
		this.tendangnhap = tendangnhap;
		this.matkhau = matkhau;
		this.hote = hote;
	}
	public String getTendangnhap() {
		return tendangnhap;
	}
	public void setTendangnhap(String tendangnhap) {
		this.tendangnhap = tendangnhap;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public String getHote() {
		return hote;
	}
	public void setHote(String hote) {
		this.hote = hote;
	}
	
}
