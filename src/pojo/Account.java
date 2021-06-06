package pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * images
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:36 AM
 * @Description
 */
public class Account {
    private int accountId;
    private String accType;
    private String username;
    private String passwd;
    private Timestamp createdAt;

    public Account(){}

    public Account(String _accType, String _username, String _passwd){
        this.accType = _accType;
        this.username = _username;
        this.passwd = _passwd;
    }
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId &&
                Objects.equals(accType, account.accType) &&
                Objects.equals(username, account.username) &&
                Objects.equals(passwd, account.passwd) &&
                Objects.equals(createdAt, account.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accType, username, passwd, createdAt);
    }
}
