package entity;

public class Cart {
    private Integer id;

    private User user;

    private Course course;

    public Cart() {
    }

    public Cart(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", course=" + course +
                '}';
    }
}