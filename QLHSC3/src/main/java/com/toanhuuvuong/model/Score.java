package com.toanhuuvuong.model;

import java.util.Date;

public class Score extends Generic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private SchoolClass schoolClass;
	private SchoolYear schoolYear;
	private ScoreType scoreType;
	private Semester semester;
	private Student student;
	private Subject subject;
	private Float value;
	private Integer ordinalNumber;

	public Score() {}
	public Score(SchoolClass schoolClass, SchoolYear schoolYear, ScoreType scoreType, Semester semester, 
			Student student, Subject subject, Float value, Integer ordinalNumber, 
			Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) 
	{
		super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
		
		this.schoolClass = schoolClass;
		this.schoolYear = schoolYear;
		this.scoreType = scoreType;
		this.semester = semester;
		this.student = student;
		this.subject = subject;
		this.value = value;
		this.ordinalNumber = ordinalNumber;
	}
	public SchoolClass getSchoolClass() 
	{
		return this.schoolClass;
	}
	public void setSchoolClass(SchoolClass schoolClass) 
	{
		this.schoolClass = schoolClass;
	}
	public SchoolYear getSchoolYear()
	{
		return this.schoolYear;
	}
	public void setSchoolYear(SchoolYear schoolYear) 
	{
		this.schoolYear = schoolYear;
	}
	public ScoreType getScoreType() 
	{
		return this.scoreType;
	}
	public void setScoreType(ScoreType scoreType)
	{
		this.scoreType = scoreType;
	}
	public Semester getSemester()
	{
		return this.semester;
	}
	public void setSemester(Semester semester)
	{
		this.semester = semester;
	}
	public Student getStudent() 
	{
		return this.student;
	}
	public void setStudent(Student student) 
	{
		this.student = student;
	}
	public Subject getSubject() 
	{
		return this.subject;
	}
	public void setSubject(Subject subject) 
	{
		this.subject = subject;
	}
	public Float getValue()
	{
		return this.value;
	}
	public void setValue(Float value) 
	{
		this.value = value;
	}
	public Integer getOrdinalNumber() 
	{
		return this.ordinalNumber;
	}
	public void setOrdinalNumber(Integer ordinalNumber) 
	{
		this.ordinalNumber = ordinalNumber;
	}
}