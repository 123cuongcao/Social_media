package ra.academy.model;

public class PostTagUser {
    private Long tagId;
    private Long userId;
    private Long postId;

    public PostTagUser() {
    }

    public PostTagUser(Long tagId, Long userId, Long postId) {
        this.tagId = tagId;
        this.userId = userId;
        this.postId = postId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
