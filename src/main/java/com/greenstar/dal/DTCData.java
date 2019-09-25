package com.greenstar.dal;

import com.greenstar.entity.dtc.DTCForm;
import com.greenstar.entity.dtc.District;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 18/9/2019
 */
public class DTCData {
    private String name;
    private String code;

    List<District> districts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
