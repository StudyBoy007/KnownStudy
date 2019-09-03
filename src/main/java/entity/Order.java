package entity;

import java.util.List;

public class Order {
    private Integer id;

    private Integer courseCount;

    private boolean status;

    private Double priceTotal;

    //    private Integer userId;
    private User user;

    private String createTime;

    private boolean degree;

    private List<Course> courses;


    public Order() {
    }

    public Order(Integer courseCount, boolean status, Double priceTotal, User user, boolean degree) {
        this.courseCount = courseCount;
        this.status = status;
        this.priceTotal = priceTotal;
        this.user = user;
        this.degree = degree;
    }

    public Order(Integer courseCount, boolean status, Double priceTotal, User user) {
        this.courseCount = courseCount;
        this.status = status;
        this.priceTotal = priceTotal;
        this.user = user;
    }

    public Order(Integer courseCount, boolean status, Double priceTotal, User user, String createTime, boolean degree) {
        this.courseCount = courseCount;
        this.status = status;
        this.priceTotal = priceTotal;
        this.user = user;
        this.createTime = createTime;
        this.degree = degree;
    }

    public Order(Integer courseCount, boolean status, Double priceTotal, User user, String createTime) {
        this.courseCount = courseCount;
        this.status = status;
        this.priceTotal = priceTotal;
        this.user = user;
        this.createTime = createTime;
    }

    public Order(Integer id, Integer courseCount, boolean status, Double priceTotal, User user, String createTime, boolean degree, List<Course> courses) {
        this.id = id;
        this.courseCount = courseCount;
        this.status = status;
        this.priceTotal = priceTotal;
        this.user = user;
        this.createTime = createTime;
        this.degree = degree;
        this.courses = courses;
    }


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isDegree() {
        return degree;
    }

    public void setDegree(boolean degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", courseCount=" + courseCount +
                ", status=" + status +
                ", priceTotal=" + priceTotal +
                ", user=" + user +
                ", createTime='" + createTime + '\'' +
                ", degree=" + degree +
                ", courses=" + courses +
                '}';
    }
}