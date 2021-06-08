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
public class Student {
    private int id;
    private String studentId;
    private String fullname;
    private String gender;
    private Date dob;
    private String stuAddress;
    private Account account;
    private Clazz classCode;
    private Clazz clazz;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(studentId, student.studentId) &&
                Objects.equals(fullname, student.fullname) &&
                Objects.equals(gender, student.gender) &&
                Objects.equals(dob, student.dob) &&
                Objects.equals(stuAddress, student.stuAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, fullname, gender, dob, stuAddress);
    }
}
