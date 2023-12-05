package ra.academy.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class Reel {
    private Long reelId;
    private Long userId;
    public String upload_url;

    public Reel() {
    }

    public Reel(Long reelId, Long userId, String upload_url) {
        this.reelId = reelId;
        this.userId = userId;
        this.upload_url = upload_url;
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

    public String getUpload_url() {
        return upload_url;
    }

    public void setUpload_url(String upload_url) {
        this.upload_url = upload_url;
    }
}