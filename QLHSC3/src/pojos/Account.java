package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import qlhsc3.Generic;
import java.util.Date;

/**
 * Account generated by hbm2java
 */
public class Account extends Generic implements java.io.Serializable {

    private Role role;
    private String username;
    private String password;

    public Account() {
    }

    public Account(Role role, String username, String password, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
