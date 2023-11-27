package ra.academy.model;

public class PostTopic {
    private Long postId;
    private Topic topic;

    public PostTopic() {
    }

    public PostTopic(Long postId, Topic topic) {
        this.postId = postId;
        this.topic = topic;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
