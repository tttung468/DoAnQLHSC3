package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import qlhsc3.Generic;

/**
 * Regulation generated by hbm2java
 */
public class Regulation extends Generic implements java.io.Serializable {

    private Float maximumScore;
    private Integer minimumCapacity;
    private Integer maximumCapacity;
    private Integer minimumAge;
    private Integer maximumAge;

    public Regulation() {
    }

    public Regulation(Float maximumScore, Integer minimumCapacity, Integer maximumCapacity, Integer minimumAge, Integer maximumAge, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.maximumScore = maximumScore;
        this.minimumCapacity = minimumCapacity;
        this.maximumCapacity = maximumCapacity;
        this.minimumAge = minimumAge;
        this.maximumAge = maximumAge;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public Float getMaximumScore() {
        return this.maximumScore;
    }

    public void setMaximumScore(Float maximumScore) {
        this.maximumScore = maximumScore;
    }

    public Integer getMinimumCapacity() {
        return this.minimumCapacity;
    }

    public void setMinimumCapacity(Integer minimumCapacity) {
        this.minimumCapacity = minimumCapacity;
    }

    public Integer getMaximumCapacity() {
        return this.maximumCapacity;
    }

    public void setMaximumCapacity(Integer maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public Integer getMinimumAge() {
        return this.minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }

    public Integer getMaximumAge() {
        return this.maximumAge;
    }

    public void setMaximumAge(Integer maximumAge) {
        this.maximumAge = maximumAge;
    }

}
