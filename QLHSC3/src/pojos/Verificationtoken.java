package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Verificationtoken generated by hbm2java
 */
public class Verificationtoken implements java.io.Serializable {

    private Long id;
    private Account account;
    private String code;
    private Date expirydate;
    private Boolean isdeleted;
    private Date createddate;
    private String createdby;
    private Date modifieddate;
    private String modifiedby;

    public Verificationtoken() {
    }

    public Verificationtoken(Date expirydate, Date createddate, Date modifieddate) {
        this.expirydate = expirydate;
        this.createddate = createddate;
        this.modifieddate = modifieddate;
    }

    public Verificationtoken(Account account, String code, Date expirydate, Boolean isdeleted, Date createddate, String createdby, Date modifieddate, String modifiedby) {
        this.account = account;
        this.code = code;
        this.expirydate = expirydate;
        this.isdeleted = isdeleted;
        this.createddate = createddate;
        this.createdby = createdby;
        this.modifieddate = modifieddate;
        this.modifiedby = modifiedby;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getExpirydate() {
        return this.expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
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

}
