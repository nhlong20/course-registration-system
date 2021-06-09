package pojo;

import java.util.Objects;

/**
 * images
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:36 AM
 * @Description
 */
public class Course {
    private String courseId;
    private String dayOfWeek;
    private int maximumSlots;
    private Subject subject;
    private Teacher teacher;
    private Shift shift;
    private Semester semester;
    private String room;

    public Course() {
    }

    public Course(String courseId, String dayOfWeek, int maximumSlots, Subject subject, Teacher teacher, Shift shift, Semester semester, String room) {
        this.courseId = courseId;
        this.dayOfWeek = dayOfWeek;
        this.maximumSlots = maximumSlots;
        this.subject = subject;
        this.teacher = teacher;
        this.shift = shift;
        this.semester = semester;
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getMaximumSlots() {
        return maximumSlots;
    }

    public void setMaximumSlots(int maximumSlots) {
        this.maximumSlots = maximumSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId &&
                maximumSlots == course.maximumSlots &&
                Objects.equals(dayOfWeek, course.dayOfWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, dayOfWeek, maximumSlots);
    }
}
