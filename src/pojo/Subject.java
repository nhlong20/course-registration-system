package pojo;

import java.util.Objects;
import java.util.Set;

/**
 * pojo
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/11/2021 - 11:27 PM
 * @Description
 */
public class Subject {
    private String subjectId;
    private String subjectName;
    private int credits;
    private Set<Course> courses;

    public Subject() {
    }

    public Subject(String subjectId, String subjectName, int credits) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
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
        Subject subject = (Subject) o;
        return credits == subject.credits &&
                Objects.equals(subjectId, subject.subjectId) &&
                Objects.equals(subjectName, subject.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, subjectName, credits);
    }
}
