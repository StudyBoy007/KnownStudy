package entity;

import java.lang.ref.PhantomReference;
import java.util.List;

public class Comment {
    private Integer id;

    private User user;

    private Integer videoId;

    private String content;

    private String time;

    private Integer focus;

    private List<Replay> replays;

    private String timeInterval;

    private boolean userIsOrNot;

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

    public List<Replay> getReplays() {
        return replays;
    }

    public void setReplays(List<Replay> replays) {
        this.replays = replays;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public boolean isUserIsOrNot() {
        return userIsOrNot;
    }

    public void setUserIsOrNot(boolean userIsOrNot) {
        this.userIsOrNot = userIsOrNot;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getFocus() {
        return focus;
    }

    public void setFocus(Integer focus) {
        this.focus = focus;
    }


    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", videoId=" + videoId +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", focus=" + focus +
                ", replays=" + replays +
                ", timeInterval='" + timeInterval + '\'' +
                ", userIsOrNot=" + userIsOrNot +
                '}';
    }
}