package pojo;

import java.sql.Date;
import java.util.Objects;

/**
 * images
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:36 AM
 * @Description
 */
public class Moderator {
    private int moderatorId;
    private String fullname;
    private String gender;
    private Date dob;
    private String modAddress;

    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getModAddress() {
        return modAddress;
    }

    public void setModAddress(String modAddress) {
        this.modAddress = modAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moderator moderator = (Moderator) o;
        return moderatorId == moderator.moderatorId &&
                Objects.equals(fullname, moderator.fullname) &&
                Objects.equals(gender, moderator.gender) &&
                Objects.equals(dob, moderator.dob) &&
                Objects.equals(modAddress, moderator.modAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moderatorId, fullname, gender, dob, modAddress);
    }
}