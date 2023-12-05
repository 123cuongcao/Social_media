package ra.academy.service;

import ra.academy.model.User;

import java.util.List;

public interface IPostTagUserService {
    List<User> fillAllFriend(Long userId);
}
