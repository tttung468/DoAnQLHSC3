package com.toanhuuvuong.model;

import java.util.Date;

public class Performance extends Generic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private Float lowerBound;
	private Float upperBound;
	private Float control;

	public Performance() {}

	public Performance(String code, String name, Float lowerBound, Float upperBound, Float control, Boolean isDeleted,
			Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
		super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
		
		this.code = code;
		this.name = name;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.control = control;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getLowerBound() {
		return this.lowerBound;
	}

	public void setLowerBound(Float lowerBound) {
		this.lowerBound = lowerBound;
	}

	public Float getUpperBound() {
		return this.upperBound;
	}

	public void setUpperBound(Float upperBound) {
		this.upperBound = upperBound;
	}

	public Float getControl() {
		return this.control;
	}

	public void setControl(Float control) {
		this.control = control;
	}

}
