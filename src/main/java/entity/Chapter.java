package entity;

import java.util.List;

public class Chapter {
    private Integer id;

    private Integer courseid;

    private String chapterName;

    private String path;

    private List<Video> videos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", courseid=" + courseid +
                ", chapterName='" + chapterName + '\'' +
                ", path='" + path + '\'' +
                ", videos=" + videos +
                '}';
    }
}