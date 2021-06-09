package pojo;

import java.util.Objects;
import java.util.Set;

/**
 * images
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:36 AM
 * @Description
 */
public class Clazz {
    private String classCode;
    private int classYear;
    private Set<Student> students;

    public Clazz(){}

    public Clazz(String classCode, int classYear){
        this.classYear = classYear;
        this.classCode = classCode;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String className) {
        this.classCode = className;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return  classYear == clazz.classYear &&
                Objects.equals(classCode, clazz.classCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classYear, classCode);
    }
}
