package entity;

import java.io.Serializable;
import java.util.List;

public class Teacher{
    private Integer id;

    private String tname;

    private String introduction;

    private Integer teacherStatus;

    private Integer focus;

    private String avatar;

    private CourseClass major;

    private List<Course> courses;

    public Teacher(Integer id) {
        this.id = id;
    }

    public Teacher() {
    }

    public Teacher(CourseClass major) {
        this.major = major;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public Integer getFocus() {
        return focus;
    }

    public void setFocus(Integer focus) {
        this.focus = focus;
    }


    //    public String getMajor() {
//        return major;
//    }
//
//    public void setMajor(String major) {
//        this.major = major == null ? null : major.trim();
//    }


    public CourseClass getMajor() {
        return major;
    }

    public void setMajor(CourseClass major) {
        this.major = major;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(Integer teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", tname='" + tname + '\'' +
                ", introduction='" + introduction + '\'' +
                ", teacherStatus=" + teacherStatus +
                ", focus=" + focus +
                ", avatar='" + avatar + '\'' +
                ", major=" + major +
                ", courses=" + courses +
                '}';
    }
}