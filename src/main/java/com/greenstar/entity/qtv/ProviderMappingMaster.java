package com.greenstar.entity.qtv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 15th March, 2021
 */
@Entity
@Table(name="HS_MAPPING_TEMP")
public class ProviderMappingMaster {

    @Id
    @Column(name="PROVIDER_CODE")
    private String providerCode;

    @Column(name="PROVIDER_NAME")
    private String providerName;

    @Column(name="TERRITORY_CODE")
    private String territoryCode;

    @Column(name="CHO_CODE")
    private String choCode;

    @Column(name="CHO_NAME")
    private String choName;

    @Column(name="AM_CODE")
    private String amCode;

    @Column(name="AM_NAME")
    private String amName;

    @Column(name="DONOR")
    private String donor;

    @Column(name="UPDATED_DATE")
    private String updatedDate;




}
