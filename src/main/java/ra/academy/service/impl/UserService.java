package ra.academy.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ra.academy.dao.IUserDao;
import ra.academy.dto.UserLogin;
import ra.academy.dto.UserRegister;
import ra.academy.model.User;
import ra.academy.service.IUserService;
import ra.academy.service.UploadService;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private UploadService uploadService;

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
}