package ra.academy.model;

public class PostTopic {
    private Long postTopicId;
    private Long topicId;
    private Long postId;

    public PostTopic() {
    }

    public PostTopic(Long postTopicId, Long topicId, Long postId) {
        this.postTopicId = postTopicId;
        this.topicId = topicId;
        this.postId = postId;
    }

    public Long getPostTopicId() {
        return postTopicId;
    }

    public void setPostTopicId(Long postTopicId) {
        this.postTopicId = postTopicId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
