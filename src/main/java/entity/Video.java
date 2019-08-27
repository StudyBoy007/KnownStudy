package entity;

public class Video {
    private Integer id;

    private Integer chapterid;

    private String videoName;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChapterid() {
        return chapterid;
    }

    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", chapterid=" + chapterid +
                ", videoName='" + videoName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}