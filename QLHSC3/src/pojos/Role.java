package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import qlhsc3.Generic;

/**
 * Role generated by hbm2java
 */
public class Role extends Generic implements java.io.Serializable {

    private String code;
    private String name;
    private Integer priority;

    public Role() {
    }
    
    public Role(String code, String name, Integer priority, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.code = code;
        this.name = name;
        this.priority = priority;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
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

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
