package pojo;

import java.util.Objects;

/**
 * images
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:36 AM
 * @Description
 */
public class Subject {
    private String subjectId;
    private String subjectName;
    private int credits;

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
