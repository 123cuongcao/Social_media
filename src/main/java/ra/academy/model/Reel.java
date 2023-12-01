package ra.academy.model;

import java.util.Date;

public class Reel {
    private Long reelId;
    private Long userId;
    private String content;
    private Date postedAt;
    private Privacy privacy;
    private boolean status;

    public Reel() {
    }

    public Reel(Long reelId, Long userId, String content, Date postedAt, Privacy privacy, boolean status) {
        this.reelId = reelId;
        this.userId = userId;
        this.content = content;
        this.postedAt = postedAt;
        this.privacy = privacy;
        this.status = status;
    }

    public Long getReelId() {
        return reelId;
    }

    public void setReelId(Long reelId) {
        this.reelId = reelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
