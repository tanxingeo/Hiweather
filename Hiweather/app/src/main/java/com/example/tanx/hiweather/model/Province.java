package com.example.tanx.hiweather.model;

/**
 * Created by tanx on 2015-12-24.
 */
public class Province {
    private int id;
    private String provinceName;
    private String provinceCode;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode() {

        return provinceCode;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;

    }

    public String getProvinceName() {

        return provinceName;
    }
}
