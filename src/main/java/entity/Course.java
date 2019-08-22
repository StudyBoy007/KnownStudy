package entity;

public class Course {
    private Integer id;

    private String cname;

    private String video;

    private String chapter;

    private Integer directionId;

    private Integer isfress;

    private Double price;

    private String pic;

    private Integer courseStatus;

    private String start_time;

    private String time;

    private Integer focus;

    private Teacher teacher;

    private String introduction;

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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter == null ? null : chapter.trim();
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", video='" + video + '\'' +
                ", chapter='" + chapter + '\'' +
                ", directionId=" + directionId +
                ", isfress=" + isfress +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", courseStatus=" + courseStatus +
                ", start_time='" + start_time + '\'' +
                ", time='" + time + '\'' +
                ", focus=" + focus +
                ", teacher=" + teacher +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}