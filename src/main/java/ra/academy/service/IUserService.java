package ra.academy.service;

import ra.academy.dto.UserLogin;
import ra.academy.dto.UserRegister;
import ra.academy.model.User;

public interface IUserService {
    void save(UserRegister userRegister);

    User login(UserLogin userLogin);
}
