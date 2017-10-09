package com.loxon.javaidealab.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity encapsulating zip code and city
 * @author kissz
 */
@Entity
@Table(name = "T_ZIPCODE")
public class ZipCode implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zipCode;
	
    @Column
    private String city;
/*FIXME @deprecated* /
    @Column
    private String dummy01;

    @Column
    private String dummy02;

    @Column
    private String dummy03;

    @Column
    private String dummy04;

    @Column
    private String dummy05;

    @Column
    private String dummy06;

    @Column
    private String dummy07;

    @Column
    private String dummy08;

    @Column
    private String dummy09;

    @Column
    private String dummy10;

    @Column
    private String dummy11;

    @Column
    private String dummy12;

    @Column
    private String dummy13;

    @Column
    private String dummy14;

    @Column
    private String dummy15;

    @Column
    private String dummy16;

    @Column
    private String dummy17;

    @Column
    private String dummy18;

    @Column
    private String dummy19;

    @Column
    private String dummy20;
/**/
	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "ZipCode [zipCode=" + zipCode + ", city=" + city + "]";
	}

}