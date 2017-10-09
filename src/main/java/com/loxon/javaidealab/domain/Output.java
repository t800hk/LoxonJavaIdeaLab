package com.loxon.javaidealab.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity encapsulating output data that was transformed from {@link Output} using {@link ZipCode}
 * @author kissz
 */
@Entity
@Table(name = "T_OUTPUT")
public class Output implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    /*FIXME: 2/9:* /@GeneratedValue(strategy = GenerationType.AUTO)/**/
    /*FIXME: 2/10:*/
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_output")
    @SequenceGenerator(name="seq_output", sequenceName="seq_output", allocationSize = 10000)
    /**/
    private long id;
	
    @Column
    private String city;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
		return "Output [id=" + id + ", city=" + city + ", address=" + address + ", contractDate=" + contractDate
				+ ", buildYear=" + buildYear + "]";
	}
}