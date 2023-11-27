package ra.academy.model;

import java.util.Date;

public class LikePost {
    private Long likePostId;
    private Long postId;
    private Long userId;
    private Date likedAt;

    public LikePost() {
    }

    public LikePost(Long likePostId, Long postId, Long userId, Date likedAt) {
        this.likePostId = likePostId;
        this.postId = postId;
        this.userId = userId;
        this.likedAt = likedAt;
    }

    public Long getLikePostId() {
        return likePostId;
    }

    public void setLikePostId(Long likePostId) {
        this.likePostId = likePostId;
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

    public Date getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Date likedAt) {
        this.likedAt = likedAt;
    }
}
