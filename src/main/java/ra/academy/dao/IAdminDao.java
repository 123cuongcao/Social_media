package ra.academy.dao;

import ra.academy.model.User;

import java.util.List;

public interface IAdminDao extends IGenericDao<User,Long>{
    int banOrUnbanUser(long idUser);
     List<User> findAllP(int limit, int offset);
     List<User> findAllByName(int limit, int offset ,String name);

}
