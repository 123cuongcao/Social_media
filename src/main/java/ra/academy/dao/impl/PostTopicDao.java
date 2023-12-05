package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IPostTopicDao;
import ra.academy.model.Topic;
import ra.academy.model.TopicName;

import java.util.List;

@Component
public class PostTopicDao implements IPostTopicDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Topic> findAll() {
        String sql = "call proc_find_all_topic()";
        List<Topic> list = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Topic topic = new Topic();
                    topic.setTopicId(rs.getLong("topic_id"));
                    topic.setTopicName(TopicName.valueOf(rs.getString("topic")));
                    return topic;
                });
        return list;
    }

    @Override
    public Topic findById(Long id) {
        return null;
    }

    @Override
    public int save(Topic topic) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {

        return 0;
    }

    @Override
    public List<Topic> findTopicsByPostId(Long postId) {
        String sql = "call proc_find_topic_by_post_id(?)";
        List<Topic> list = jdbcTemplate.query(sql, new Object[]{postId},
                (rs, rowNum) -> {
                    Topic topic = new Topic();
                    topic.setTopicId(rs.getLong("topic_id"));
                    topic.setTopicName(TopicName.valueOf(rs.getString("topic")));
                    return topic;
                });
        return list;
    }
}
