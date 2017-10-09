package com.loxon.javaidealab.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity encapsulating input data to be transformed
 * @author kissz
 */
@Entity
@Table(name = "T_INPUT")
public class Input implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
    @Column
    private long zipCode;

    @Column
    private String address;

    @Column
    @Temporal(TemporalType.DATE)
    private Date contractDate;

    @Column
    private int buildYear;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	@Override
	public String toString() {
		return "Input [id=" + id + ", zipCode=" + zipCode + ", address=" + address + ", contractDate=" + contractDate
				+ ", buildYear=" + buildYear + "]";
	}
}