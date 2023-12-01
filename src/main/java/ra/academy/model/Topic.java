package ra.academy.model;

public class Topic {
    private Long topicId;
    private TopicName topicName;

    public Topic() {
    }

    public Topic(Long topicId, TopicName topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public TopicName getTopicName() {
        return topicName;
    }

    public void setTopicName(TopicName topicName) {
        this.topicName = topicName;
    }
}
