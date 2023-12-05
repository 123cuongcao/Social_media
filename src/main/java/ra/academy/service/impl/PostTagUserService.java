package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.IPostTagUserDao;
import ra.academy.model.User;
import ra.academy.service.IPostTagUserService;

import java.util.List;


@Service
public class PostTagUserService implements IPostTagUserService {
    @Autowired
    private IPostTagUserDao postTagUserDao;

    @Override
    public List<User> fillAllFriend(Long userId) {
        return postTagUserDao.findAllFriendId(userId);
    }
}
