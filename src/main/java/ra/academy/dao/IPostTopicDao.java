package ra.academy.dao;

import ra.academy.model.PostTopic;
import ra.academy.model.Topic;

import java.util.List;

public interface IPostTopicDao extends IGenericDao<Topic, Long> {
    List<Topic> findTopicsByPostId(Long postId);
}
