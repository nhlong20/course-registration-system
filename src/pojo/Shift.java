package pojo;

import java.sql.Time;
import java.util.Objects;
import java.util.Set;

/**
 * pojo
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/9/2021 - 6:55 PM
 * @Description
 */
public class Shift {
    private int shiftId;
    private Time startAt;
    private Time endAt;
    private Set<Course> courses;

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public Time getStartAt() {
        return startAt;
    }

    public void setStartAt(Time startAt) {
        this.startAt = startAt;
    }

    public Time getEndAt() {
        return endAt;
    }

    public void setEndAt(Time endAt) {
        this.endAt = endAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return shiftId == shift.shiftId &&
                Objects.equals(startAt, shift.startAt) &&
                Objects.equals(endAt, shift.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shiftId, startAt, endAt);
    }
}
