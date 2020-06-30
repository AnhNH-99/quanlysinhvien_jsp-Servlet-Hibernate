package com.javaweb.model;

public class LopNienChe {
	private int malopnienche;
	private String tenlopnienche;
	private String nienkhoa;
	private int siso;
	private ChuyenNganh chuyennganh;
	private GiangVien giangvien;
	public LopNienChe() {
		super();
	}
	public LopNienChe(String tenlopnienche, String nienkhoa, int siso, ChuyenNganh chuyennganh, GiangVien giangvien) {
		super();
		this.tenlopnienche = tenlopnienche;
		this.nienkhoa = nienkhoa;
		this.siso = siso;
		this.chuyennganh = chuyennganh;
		this.giangvien = giangvien;
	}
	public int getMalopnienche() {
		return malopnienche;
	}
	public void setMalopnienche(int malopnienche) {
		this.malopnienche = malopnienche;
	}
	public String getTenlopnienche() {
		return tenlopnienche;
	}
	public void setTenlopnienche(String tenlopnienche) {
		this.tenlopnienche = tenlopnienche;
	}
	public String getNienkhoa() {
		return nienkhoa;
	}
	public void setNienkhoa(String nienkhoa) {
		this.nienkhoa = nienkhoa;
	}
	public int getSiso() {
		return siso;
	}
	public void setSiso(int siso) {
		this.siso = siso;
	}
	public ChuyenNganh getChuyennganh() {
		return chuyennganh;
	}
	public void setChuyennganh(ChuyenNganh chuyennganh) {
		this.chuyennganh = chuyennganh;
	}
	public GiangVien getGiangvien() {
		return giangvien;
	}
	public void setGiangvien(GiangVien giangvien) {
		this.giangvien = giangvien;
	}
	
}
