package pojo;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * pojo
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/10/2021 - 1:04 AM
 * @Description
 */
public class CourseStudent {
    private int id;
    private Course course;
    private Student student;
    private Timestamp createdAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public CourseStudent(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public CourseStudent() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudent that = (CourseStudent) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
