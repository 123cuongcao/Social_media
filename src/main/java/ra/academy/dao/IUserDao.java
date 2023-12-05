package ra.academy.dao;

import ra.academy.dto.ChangePassword;
import ra.academy.model.RelationshipStatus;
import ra.academy.model.User;
import ra.academy.model.UserRelation;

import java.util.List;

public interface IUserDao extends IGenericDao<User, Long> {
    User findByUserEmail(String userName);

    int edit(User user);
    User findUserByPostId(Long postId);

    int changeUserRelation(long idSender, long idReceiver, String status);

    List<User> findNotFriend(long currentIdUser);

    List<User> findSentPendingFriendRequests(long currentIdUser);

    List<User> findAllFriend(long currentIdUser);

    List<User> getRequestFriendFromUser(long currenIdUser);

    int addUserRelation(long idSender, long idReceiver, String status);

    UserRelation getUserRelation(long idSender, long idReceiver);

    int deleteUserRelation(long idSender, long idReceiver);

    int changePassword( long idUser,String password);
}
