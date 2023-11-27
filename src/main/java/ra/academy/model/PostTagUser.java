package ra.academy.model;

public class PostTagUser {
    private Long postId;
    private Long taggedUserId;

    public PostTagUser() {
    }

    public PostTagUser(Long postId, Long taggedUserId) {
        this.postId = postId;
        this.taggedUserId = taggedUserId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getTaggedUserId() {
        return taggedUserId;
    }

    public void setTaggedUserId(Long taggedUserId) {
        this.taggedUserId = taggedUserId;
    }
}
