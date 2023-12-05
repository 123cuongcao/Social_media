package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.IPostTopicDao;
import ra.academy.model.Topic;
import ra.academy.service.IPostTopicService;

import java.util.List;

@Service
public class PostTopicService implements IPostTopicService {
    @Autowired
    private IPostTopicDao postTopicDao;
    @Override
    public List<Topic> findTopic() {
        return postTopicDao.findAll();
    }
}
