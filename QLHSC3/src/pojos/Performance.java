package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import qlhsc3.Generic;

/**
 * Performance generated by hbm2java
 */
public class Performance extends Generic implements java.io.Serializable {

    private String code;
    private String name;
    private Float lowerBound;
    private Float upperBound;
    private Float control;

    public Performance() {
    }

    public Performance(String code, String name, Float lowerBound, Float upperBound, Float control, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.code = code;
        this.name = name;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.control = control;
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
