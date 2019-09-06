package entity;

public class Replay {
    private Integer id;

    private Integer commentId;

    private User answer;

    private User replay;

    private String content;

    private Integer focus;

    private String timeInterval;

    private String date;

    private boolean userIsOrNot;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }


    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public User getAnswer() {
        return answer;
    }

    public void setAnswer(User answer) {
        this.answer = answer;
    }

    public User getReplay() {
        return replay;
    }

    public void setReplay(User replay) {
        this.replay = replay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getFocus() {
        return focus;
    }

    public void setFocus(Integer focus) {
        this.focus = focus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isUserIsOrNot() {
        return userIsOrNot;
    }

    public void setUserIsOrNot(boolean userIsOrNot) {
        this.userIsOrNot = userIsOrNot;
    }

    @Override
    public String toString() {
        return "Replay{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", answer=" + answer +
                ", replay=" + replay +
                ", content='" + content + '\'' +
                ", focus=" + focus +
                ", timeInterval='" + timeInterval + '\'' +
                ", date='" + date + '\'' +
                ", userIsOrNot=" + userIsOrNot +
                '}';
    }
}