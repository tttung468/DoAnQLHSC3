package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import qlhsc3.Generic;
import java.util.Date;

/**
 * ConductType generated by hbm2java
 */
public class ConductType extends Generic implements java.io.Serializable {

    private String code;
    private String name;

    public ConductType() {
    }

    public ConductType(String code, String name, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.code = code;
        this.name = name;
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

}