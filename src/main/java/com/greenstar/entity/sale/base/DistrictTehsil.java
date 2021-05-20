package com.greenstar.entity.sale.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 29th April, 2021
 */
@Entity
@Table(name="BASE_DIST_TEHSIL")
public class DistrictTehsil {

    @Id
    @Column(name="DIST_ID")
    private String DIST_ID;
    @Column(name="TEHSIL_ID")
    private String TEHSIL_ID;


}
