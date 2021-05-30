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
public class Semester {
    private int semesterId;
    private int semName;
    private int semYear;
    private Date startdate;
    private Date enddate;

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public int getSemName() {
        return semName;
    }

    public void setSemName(int semName) {
        this.semName = semName;
    }

    public int getSemYear() {
        return semYear;
    }

    public void setSemYear(int semYear) {
        this.semYear = semYear;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return semesterId == semester.semesterId &&
                semName == semester.semName &&
                semYear == semester.semYear &&
                Objects.equals(startdate, semester.startdate) &&
                Objects.equals(enddate, semester.enddate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semesterId, semName, semYear, startdate, enddate);
    }
}
