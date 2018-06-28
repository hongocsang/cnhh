package com.example.vuphu.project_hoa_hoc;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vuphu on 7/29/2017.
 */
@IgnoreExtraProperties
public class CauHoi {

    private String cauHoi;
    private String dapAn1, dapAn2, dapAn3, dapAn4;
    private String traLoi;
    public Map<String, Boolean> stars = new HashMap<>();
    public CauHoi() {

    }

    public CauHoi(String cauHoi, String dapAn1, String dapAn2, String dapAn3, String dapAn4, String traLoi) {
        this.cauHoi = cauHoi;
        this.dapAn1 = dapAn1;
        this.dapAn2 = dapAn2;
        this.dapAn3 = dapAn3;
        this.dapAn4 = dapAn4;
        this.traLoi = traLoi;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getDapAn1() {
        return dapAn1;
    }

    public void setDapAn1(String dapAn1) {
        this.dapAn1 = dapAn1;
    }

    public String getDapAn2() {
        return dapAn2;
    }

    public void setDapAn2(String dapAn2) {
        this.dapAn2 = dapAn2;
    }

    public String getDapAn3() {
        return dapAn3;
    }

    public void setDapAn3(String dapAn3) {
        this.dapAn3 = dapAn3;
    }

    public String getDapAn4() {
        return dapAn4;
    }

    public void setDapAn4(String dapAn4) {
        this.dapAn4 = dapAn4;
    }

    public String getTraLoi() {
        return traLoi;
    }

    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cauHoi", this.cauHoi);
        result.put("dapAn1", this.dapAn1);
        result.put("dapAn2", this.dapAn2);
        result.put("dapAn3", this.dapAn3);
        result.put("dapAn4", this.dapAn4);
        result.put("traLoi", this.traLoi);
        return result;
    }
}
