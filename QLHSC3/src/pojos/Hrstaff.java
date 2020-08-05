package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Hrstaff generated by hbm2java
 */
public class Hrstaff implements java.io.Serializable {

    private Long id;
    private Account account;
    private String code;
    private String name;
    private String phone;
    private String avatarpath;
    private String gender;
    private Date birth;
    private String address;
    private Integer salary;
    private Boolean isdeleted;
    private Date createddate;
    private String createdby;
    private Date modifieddate;
    private String modifiedby;

    public Hrstaff() {
    }

    public Hrstaff(Date createddate, Date modifieddate) {
        this.createddate = createddate;
        this.modifieddate = modifieddate;
    }

    public Hrstaff(Account account, String code, String name, String phone, String avatarpath, String gender, Date birth, String address, Integer salary, Boolean isdeleted, Date createddate, String createdby, Date modifieddate, String modifiedby) {
        this.account = account;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.avatarpath = avatarpath;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.salary = salary;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarpath() {
        return this.avatarpath;
    }

    public void setAvatarpath(String avatarpath) {
        this.avatarpath = avatarpath;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
