package pojo;

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
