package entity;

import java.util.List;

public class Order {
    private Integer id;

    private Integer courseCount;

    private boolean status;

    private Double priceTotal;

    private Integer userId;

    private List<Course> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", courseCount=" + courseCount +
                ", status=" + status +
                ", priceTotal=" + priceTotal +
                ", userId=" + userId +
                ", courses=" + courses +
                '}';
    }
}