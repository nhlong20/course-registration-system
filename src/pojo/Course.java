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
    private int courseId;
    private String dayOfWeek;
    private int maximumSlots;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
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
