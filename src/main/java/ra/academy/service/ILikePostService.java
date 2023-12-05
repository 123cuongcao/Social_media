package ra.academy.service;

import ra.academy.model.LikePost;

import java.util.List;

public interface ILikePostService {
    List<LikePost> findAll();
    int deleteById(Long id);
}
