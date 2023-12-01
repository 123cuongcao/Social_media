package ra.academy.dao;

import ra.academy.model.User;

public interface IReelDao extends IGenericDao<User, Long> {
    User findByUserEmail(String userName);
}
