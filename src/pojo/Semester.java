package pojo;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;

/**
 * pojo
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/8/2021 - 11:36 PM
 * @Description
 */
public class Semester {
    private int semesterId;
    private String semName;
    private int semYear;
    private boolean isCurrentSem;
    private Date startdate;
    private Date enddate;
    private Set<Course> courses;


    public Semester() {
    }

    public Semester(String semName, int semYear, Date startdate, Date enddate) {
        this.semName = semName;
        this.semYear = semYear;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
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

    public boolean getIsCurrentSem() {
        return isCurrentSem;
    }

    public void setIsCurrentSem(boolean isCurrentSem) {
        this.isCurrentSem = isCurrentSem;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return semesterId == semester.semesterId &&
                semYear == semester.semYear &&
                Objects.equals(semName, semester.semName) &&
                isCurrentSem == semester.isCurrentSem &&
                Objects.equals(startdate, semester.startdate) &&
                Objects.equals(enddate, semester.enddate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semesterId, semName, semYear, isCurrentSem, startdate, enddate);
    }
}
