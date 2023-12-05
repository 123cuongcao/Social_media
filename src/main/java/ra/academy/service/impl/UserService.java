package ra.academy.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.IUserDao;
import ra.academy.dto.FriendRelationReponse;
import ra.academy.dto.UserEditInfor;
import ra.academy.dto.UserLogin;
import ra.academy.dto.UserRegister;
import ra.academy.model.RelationshipStatus;
import ra.academy.model.RelationshipStatus;
import ra.academy.model.User;
import ra.academy.model.UserRelation;
import ra.academy.model.UserRelation;
import ra.academy.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private UploadService uploadService;

    @Override
    public void edit(UserEditInfor userEditInfor) {
        String imageUrl = null;
        if (!userEditInfor.getAvatarUrl().isEmpty()) {
            // xử lí upload file
            imageUrl = uploadService.uploadFile(userEditInfor.getAvatarUrl());
        }
        User user = new User(
                userEditInfor.getFullName(), userEditInfor.getEmail(),
                userEditInfor.getPhoneNumber(), imageUrl,
                userEditInfor.getDateOfBirth());
        userDao.edit(user);
    }

    @Override
    public Long findUserIdByUserEmail(String email) {
        return  findAllUser().stream().filter(u->u.getEmail().equals(email)).findFirst().orElse(null).getUserId();
    }
    @Override
    public UserEditInfor findUserByEmailToEdit(String email) {
        User user = findAllUser().stream().filter(u->u.getEmail().equals(email)).findFirst().orElse(null);

        return new UserEditInfor(
                user.getFullName(),user.getEmail(),user.getPhoneNumber(),user.getDateOfBirth(),user.getAvatarUrl()
        );
    }

    @Override
    public List<User> findNotFriend(long currentUserId) {
        return userDao.findNotFriend(currentUserId);

    }

    @Override
    public List<User> findSentPendingFriendRequests(long currentUserId) {
        return userDao.findSentPendingFriendRequests(currentUserId);
    }

    @Override
    public UserRelation getUserRelation(long idSender, long idReceiver) {
        return userDao.getUserRelation(idSender, idReceiver);
    }

    @Override
    public List<User> findAllFriend(long currentIdUser) {
        return userDao.findAllFriend(currentIdUser);
    }

    @Override
    public List<User> getRequestFriendFromUser(long currentIdUser) {
        return userDao.getRequestFriendFromUser(currentIdUser);
    }

    @Override
    public int changUserRelation(long idSender, long idReceiver, RelationshipStatus status) {
        return userDao.changeUserRelation(idSender, idReceiver, status.toString());
    }


    @Override
    public int doAddFriend(long idSender, long idReceiver, RelationshipStatus status) {
        return userDao.addUserRelation(idSender, idReceiver, status.toString());

    }
    @Override
    public void register(UserRegister userRegister) {

        String imageUrl = null;
        if (!userRegister.getAvatarUrl().isEmpty()) {
            // xử lí upload file
            imageUrl = uploadService.uploadFile(userRegister.getAvatarUrl());
        }
        User user = new User(
                userRegister.getFullName(), userRegister.getEmail(),
                userRegister.getPhoneNumber(), imageUrl,
                userRegister.getDateOfBirth(),
                BCrypt.hashpw(userRegister.getPassword(), BCrypt.gensalt(12))
        );
        userDao.save(user);
    }

    @Override
    public User login(UserLogin userLogin) {
        User account = userDao.findByUserEmail(userLogin.getUserEmail());
        if (account != null && BCrypt.checkpw(userLogin.getPassword(), account.getPassword())) {
            return account;
        }
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    @Override
    public boolean checkExistByEmail(String email) {
        return findAllUser().stream().anyMatch(acc -> acc.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public boolean checkExistByPhone(String phone) {
        return findAllUser().stream().anyMatch(acc -> acc.getEmail().equalsIgnoreCase(phone));
    }

    @Override
    public void save(UserRegister userRegister) {

    }

    @Override
    public int deleteUserRelation(long idSender, long idReceiver) {
        return userDao.deleteUserRelation(idSender, idReceiver);
    }

    @Override
    public User findUserByPostId(Long postId) {
        return userDao.findUserByPostId(postId);
    }

    @Override
    public long findUserByUserEmail(String email) {
        return 0;
    }

    @Override
    public int changePassword(long idUser, String password) {
        return userDao.changePassword(idUser, BCrypt.hashpw(password, BCrypt.gensalt(12)));
    }
}
