package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import qlhsc3.Generic;

/**
 * VerificationToken generated by hbm2java
 */
public class VerificationToken extends Generic implements java.io.Serializable {

    private Account account;
    private String code;
    private Date expiryDate;

    public VerificationToken() {
    }


    public VerificationToken(Account account, String code, Date expiryDate, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.account = account;
        this.code = code;
        this.expiryDate = expiryDate;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
