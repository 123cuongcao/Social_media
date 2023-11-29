package ra.academy.service;

import ra.academy.dto.UserEditInfor;
import ra.academy.dto.UserLogin;
import ra.academy.dto.UserRegister;
import ra.academy.model.User;

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


}
