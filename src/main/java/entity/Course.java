package entity;

import java.io.Serializable;
import java.util.List;

public class Course{
    private Integer id;

    private String cname;

    //    private String video;
//
    private String course_path;

    //    private Integer directionId;
    private List<Chapter> chapters;

    private CourseClass courseDirection;

    private Integer isfress;

    private Double price;

    private String pic;

    private Integer courseStatus;

    private String start_time;

    private String time;

    private Integer focus;

    private Teacher teacher;

    private String introduction;

    private Integer buy_num;

    private String range;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(CourseClass courseDirection, Teacher teacher) {
        this.courseDirection = courseDirection;
        this.teacher = teacher;
    }

    public Course(CourseClass courseDirection) {
        this.courseDirection = courseDirection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getCourse_path() {
        return course_path;
    }

    public void setCourse_path(String course_path) {
        this.course_path = course_path;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }


    //    public String getVideo() {
//        return video;
//    }
//
//    public void setVideo(String video) {
//        this.video = video == null ? null : video.trim();
//    }
//
//    public String getChapter() {
//        return chapter;
//    }
//
//    public void setChapter(String chapter) {
//        this.chapter = chapter == null ? null : chapter.trim();
//    }

//    public Integer getDirectionId() {
//        return directionId;
//    }
//
//    public void setDirectionId(Integer directionId) {
//        this.directionId = directionId;
//    }


    public CourseClass getCourseDirection() {
        return courseDirection;
    }

    public void setCourseDirection(CourseClass courseDirection) {
        this.courseDirection = courseDirection;
    }

    public Integer getIsfress() {
        return isfress;
    }

    public void setIsfress(Integer isfress) {
        this.isfress = isfress;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Integer getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Integer courseStatus) {
        this.courseStatus = courseStatus;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getFocus() {
        return focus;
    }

    public void setFocus(Integer focus) {
        this.focus = focus;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    public Integer getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(Integer buy_num) {
        this.buy_num = buy_num;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", course_path='" + course_path + '\'' +
                ", chapters=" + chapters +
                ", courseDirection=" + courseDirection +
                ", isfress=" + isfress +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", courseStatus=" + courseStatus +
                ", start_time='" + start_time + '\'' +
                ", time='" + time + '\'' +
                ", focus=" + focus +
                ", teacher=" + teacher +
                ", introduction='" + introduction + '\'' +
                ", buy_num=" + buy_num +
                ", range='" + range + '\'' +
                '}';
    }
}