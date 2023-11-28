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

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public void save(UserRegister userRegister) {
        if (userRegister.getAvatarUrl() == null) {
            userRegister.setAvatarUrl("");
        }
        User user = new User(
                userRegister.getFullName(), userRegister.getEmail(),
                userRegister.getPhoneNumber(), userRegister.getAvatarUrl(),
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
}
