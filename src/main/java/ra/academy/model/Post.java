package ra.academy.model;

import java.util.Date;

public class Post {
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private Date postedAt;
    private Privacy privacy;
    private boolean status;

    public Post() {
    }

    public Post(Long postId, Long userId, String title, String content, Date postedAt, Privacy privacy, boolean status) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.postedAt = postedAt;
        this.privacy = privacy;
        this.status = status;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
