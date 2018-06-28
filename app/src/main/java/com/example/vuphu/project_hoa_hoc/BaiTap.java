package com.example.vuphu.project_hoa_hoc;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vuphu on 8/1/2017.
 */

public class BaiTap {

    private String deBai,cauTraLoi;
    public Map<String, Boolean> stars = new HashMap<>();

    public BaiTap(String deBai, String cauTraLoi) {
        this.deBai = deBai;
        this.cauTraLoi = cauTraLoi;
    }

    public BaiTap() {
    }

    public String getDeBai() {
        return deBai;
    }

    public void setDeBai(String deBai) {
        this.deBai = deBai;
    }

    public String getCauTraLoi() {
        return cauTraLoi;
    }

    public void setCauTraLoi(String cauTraLoi) {
        this.cauTraLoi = cauTraLoi;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("deBai", this.deBai);
        result.put("cauTraLoi", this.cauTraLoi);

        return result;
    }
}
