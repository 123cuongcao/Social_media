package ra.academy.dao;

import ra.academy.model.PostTagUser;
import ra.academy.model.User;

import java.util.List;

public interface IPostTagUserDao extends IGenericDao<PostTagUser, Long> {
    List<Long> findTaggedUserIdByPostId(Long postId);
    List<User> findAllFriendId(Long userId);
}
