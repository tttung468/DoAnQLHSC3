package pojos;
// Generated Aug 5, 2020 4:10:55 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Semester generated by hbm2java
 */
public class Semester  implements java.io.Serializable {


     private Long id;
     private String code;
     private String name;
     private Integer factor;
     private Boolean isdeleted;
     private Date createddate;
     private String createdby;
     private Date modifieddate;
     private String modifiedby;
     private Set studentofclasses = new HashSet(0);
     private Set scores = new HashSet(0);
     private Set conducts = new HashSet(0);
     private Set teacherassignments = new HashSet(0);

    public Semester() {
    }

	
    public Semester(Date createddate, Date modifieddate) {
        this.createddate = createddate;
        this.modifieddate = modifieddate;
    }
    public Semester(String code, String name, Integer factor, Boolean isdeleted, Date createddate, String createdby, Date modifieddate, String modifiedby, Set studentofclasses, Set scores, Set conducts, Set teacherassignments) {
       this.code = code;
       this.name = name;
       this.factor = factor;
       this.isdeleted = isdeleted;
       this.createddate = createddate;
       this.createdby = createdby;
       this.modifieddate = modifieddate;
       this.modifiedby = modifiedby;
       this.studentofclasses = studentofclasses;
       this.scores = scores;
       this.conducts = conducts;
       this.teacherassignments = teacherassignments;
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
    public Set getStudentofclasses() {
        return this.studentofclasses;
    }
    
    public void setStudentofclasses(Set studentofclasses) {
        this.studentofclasses = studentofclasses;
    }
    public Set getScores() {
        return this.scores;
    }
    
    public void setScores(Set scores) {
        this.scores = scores;
    }
    public Set getConducts() {
        return this.conducts;
    }
    
    public void setConducts(Set conducts) {
        this.conducts = conducts;
    }
    public Set getTeacherassignments() {
        return this.teacherassignments;
    }
    
    public void setTeacherassignments(Set teacherassignments) {
        this.teacherassignments = teacherassignments;
    }




}


