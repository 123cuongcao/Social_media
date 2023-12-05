package ra.academy.dao;

import ra.academy.model.User;

public interface IUserDao extends IGenericDao<User, Long> {
    User findByUserEmail(String userName);

    int edit(User user);
    User findUserByPostId(Long postId);
}
