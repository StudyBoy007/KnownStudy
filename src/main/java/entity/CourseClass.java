package entity;

public class CourseClass {
    private Integer id;

    private String courseDirection;

    public CourseClass() {
    }

    public CourseClass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseDirection() {
        return courseDirection;
    }

    public void setCourseDirection(String courseDirection) {
        this.courseDirection = courseDirection == null ? null : courseDirection.trim();
    }

    @Override
    public String toString() {
        return "CourseClass{" +
                "id=" + id +
                ", courseDirection='" + courseDirection + '\'' +
                '}';
    }
}