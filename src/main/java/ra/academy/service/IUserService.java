package ra.academy.service;

import ra.academy.dto.FriendRelationReponse;
import ra.academy.dto.UserEditInfor;
import ra.academy.dto.UserLogin;
import ra.academy.dto.UserRegister;
import ra.academy.model.RelationshipStatus;
import ra.academy.model.User;
import ra.academy.model.UserRelation;

import java.util.List;

public interface IUserService {
    void save(UserRegister userRegister);

    User login(UserLogin userLogin);

    boolean checkExistByEmail(String email);

    boolean checkExistByPhone(String phone);

    List<User> findAllUser();

    void register(UserRegister formRegister);

    void edit(UserEditInfor userEditInfor);

    UserEditInfor findUserByEmailToEdit(String email);

    int doAddFriend(long idSender, long idReceiver, RelationshipStatus status);

    List<User> findNotFriend(long currentUserId);

    List<User> findSentPendingFriendRequests( long currentUserId);
    List<User> findAllFriend (long currentIdUser);
    List<User> getRequestFriendFromUser(long currentIdUser);
    int changUserRelation(long id,long idReceiver, RelationshipStatus status);

    UserRelation getUserRelation(long idSender ,long idReceiver);
    long findUserByUserEmail(String email);
    int deleteUserRelation(long idSender , long idReceiver);

    int  changePassword(long idUser , String password);


    Long findUserIdByUserEmail(String email);
    User findUserByPostId(Long postId);
}
