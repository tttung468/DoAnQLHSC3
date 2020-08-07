package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Subject generated by hbm2java
 */
public class Subject implements java.io.Serializable {

    private Long id;
    private String code;
    private String name;
    private Integer classHours;
    private Integer factor;
    private Boolean isdeleted;
    private Date createddate;
    private String createdby;
    private Date modifieddate;
    private String modifiedby;
    private Set scores = new HashSet(0);
    private Set teachers = new HashSet(0);
    private Set teacherassignments = new HashSet(0);
    private Set headmasters = new HashSet(0);

    public Subject() {
    }

    public Subject(Date createddate, Date modifieddate) {
        this.createddate = createddate;
        this.modifieddate = modifieddate;
    }

    public Subject(String code, String name, Integer classHours, Integer factor, Boolean isdeleted, Date createddate, String createdby, Date modifieddate, String modifiedby, Set scores, Set teachers, Set teacherassignments, Set headmasters) {
        this.code = code;
        this.name = name;
        this.classHours = classHours;
        this.factor = factor;
        this.isdeleted = isdeleted;
        this.createddate = createddate;
        this.createdby = createdby;
        this.modifieddate = modifieddate;
        this.modifiedby = modifiedby;
        this.scores = scores;
        this.teachers = teachers;
        this.teacherassignments = teacherassignments;
        this.headmasters = headmasters;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getClassHours() {
        return this.classHours;
    }

    public void setClassHours(Integer classHours) {
        this.classHours = classHours;
    }

    public Integer getFactor() {
        return this.factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    public Boolean getIsdeleted() {
        return this.isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Date getCreateddate() {
        return this.createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getCreatedby() {
        return this.createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getModifieddate() {
        return this.modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public String getModifiedby() {
        return this.modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Set getScores() {
        return this.scores;
    }

    public void setScores(Set scores) {
        this.scores = scores;
    }

    public Set getTeachers() {
        return this.teachers;
    }

    public void setTeachers(Set teachers) {
        this.teachers = teachers;
    }

    public Set getTeacherassignments() {
        return this.teacherassignments;
    }

    public void setTeacherassignments(Set teacherassignments) {
        this.teacherassignments = teacherassignments;
    }

    public Set getHeadmasters() {
        return this.headmasters;
    }

    public void setHeadmasters(Set headmasters) {
        this.headmasters = headmasters;
    }

}