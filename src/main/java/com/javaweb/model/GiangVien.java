package com.javaweb.model;

import java.sql.Date;

public class GiangVien {
	private int magiangvien;
	private String tengiangvien;
	private Date ngaysinh;
	private String sodienthoai;
	private byte gioitinh;
	private String diachi;
	private String email;
	private Khoa khoa;
	public GiangVien() {
		super();
	}
	public GiangVien(String tengiangvien, Date ngaysinh, String sodienthoai, byte gioitinh, String diachi, String email,
			Khoa khoa) {
		super();
		this.tengiangvien = tengiangvien;
		this.ngaysinh = ngaysinh;
		this.sodienthoai = sodienthoai;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.email = email;
		this.khoa = khoa;
	}
	public int getMagiangvien() {
		return magiangvien;
	}
	public void setMagiangvien(int magiangvien) {
		this.magiangvien = magiangvien;
	}
	public String getTengiangvien() {
		return tengiangvien;
	}
	public void setTengiangvien(String tengiangvien) {
		this.tengiangvien = tengiangvien;
	}
	public Date getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	public byte getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(byte gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Khoa getKhoa() {
		return khoa;
	}
	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}
	
}
