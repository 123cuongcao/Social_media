package ra.academy.dao.impl;

import ra.academy.dao.ILikePostDao;
import ra.academy.model.LikePost;

import java.util.List;

public class LikePostDao implements ILikePostDao {
    @Override
    public List<LikePost> findAll() {
        return null;
    }

    @Override
    public LikePost findById(Long id) {
        return null;
    }

    @Override
    public int save(LikePost likePost) {
        return 0;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<LikePost> findLikesByPostId(Long postId) {
        return null;
    }
}
