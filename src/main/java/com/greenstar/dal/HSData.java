package com.greenstar.dal;

import com.greenstar.entity.SDDepot;
import com.greenstar.entity.qtv.Providers;

import java.io.Serializable;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */
public class HSData implements Serializable {
    private String name;
    private String code;
    private String AMName;
    private String region;
    
    List<Providers> providers;

    public List<Providers> getProviders() {
        return providers;
    }

    public void setProviders(List<Providers> providers) {
        this.providers = providers;
    }
}
