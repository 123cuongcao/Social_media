package ra.academy.model;

import java.util.Date;

public class LikeComment {
    private Long likeCommentId;
    private Long commentId;
    private Long userId;
    private Date likedAt;

    public LikeComment() {
    }

    public LikeComment(Long likeCommentId, Long commentId, Long userId, Date likedAt) {
        this.likeCommentId = likeCommentId;
        this.commentId = commentId;
        this.userId = userId;
        this.likedAt = likedAt;
    }

    public Long getLikeCommentId() {
        return likeCommentId;
    }

    public void setLikeCommentId(Long likeCommentId) {
        this.likeCommentId = likeCommentId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
