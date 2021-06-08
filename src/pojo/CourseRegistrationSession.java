package pojo;

import java.sql.Date;

/**
 * pojo
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/8/2021 - 3:49 PM
 * @Description
 */
public class CourseRegistrationSession {
    private int registrationSessionId;
    private Date  startDate;
    private Date endDate;
    private int semesterId;

    public CourseRegistrationSession() {
    }

    public CourseRegistrationSession(Date startDate, Date endDate, int semesterId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.semesterId = semesterId;
    }

    public int getRegistrationSessionId() {
        return registrationSessionId;
    }

    public void setRegistrationSessionId(int registrationSessionId) {
        this.registrationSessionId = registrationSessionId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }



}
