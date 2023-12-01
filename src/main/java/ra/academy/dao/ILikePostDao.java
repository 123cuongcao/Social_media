package ra.academy.dao;

import ra.academy.model.LikePost;

import java.util.List;

public interface ILikePostDao extends IGenericDao<LikePost, Long> {
    List<LikePost> findLikesByPostId(Long postId);
}
