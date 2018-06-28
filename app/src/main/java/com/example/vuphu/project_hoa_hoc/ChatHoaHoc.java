package com.example.vuphu.project_hoa_hoc;

/**
 * Created by vuphu on 7/29/2017.
 */

public class ChatHoaHoc {

    private String name;
    private double nguyenTuKhoi;
    private int hoaTri;
    private String kiHieu;
    private String video;
    private String phanLoai;
    private String trangThaiVatChat;
    private float doAmDien;
    private String cauHinhElectron;

    public ChatHoaHoc(String name, double nguyenTuKhoi, int hoaTri,
                      String kiHieu, String video, String phanLoai, String trangThaiVatChat,
                      float doAmDien, String cauHinhElectron) {
        this.name = name;
        this.nguyenTuKhoi = nguyenTuKhoi;
        this.hoaTri = hoaTri;
        this.kiHieu = kiHieu;
        this.video = video;
        this.phanLoai = phanLoai;
        this.trangThaiVatChat = trangThaiVatChat;
        this.doAmDien = doAmDien;
        this.cauHinhElectron = cauHinhElectron;
    }

    public ChatHoaHoc() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNguyenTuKhoi() {
        return nguyenTuKhoi;
    }

    public void setNguyenTuKhoi(double nguyenTuKhoi) {
        this.nguyenTuKhoi = nguyenTuKhoi;
    }

    public int getHoaTri() {
        return hoaTri;
    }

    public void setHoaTri(int hoaTri) {
        this.hoaTri = hoaTri;
    }

    public String getKiHieu() {
        return kiHieu;
    }

    public void setKiHieu(String kiHieu) {
        this.kiHieu = kiHieu;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getTrangThaiVatChat() {
        return trangThaiVatChat;
    }

    public void setTrangThaiVatChat(String trangThaiVatChat) {
        this.trangThaiVatChat = trangThaiVatChat;
    }

    public float getDoAmDien() {
        return doAmDien;
    }

    public void setDoAmDien(float doAmDien) {
        this.doAmDien = doAmDien;
    }

    public String getCauHinhElectron() {
        return cauHinhElectron;
    }

    public void setCauHinhElectron(String cauHinhElectron) {
        this.cauHinhElectron = cauHinhElectron;
    }
}
