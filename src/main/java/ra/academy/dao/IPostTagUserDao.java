package ra.academy.dao;

import ra.academy.model.PostTagUser;

import java.util.List;

public interface IPostTagUserDao extends IGenericDao<PostTagUser, Long> {
    List<Long> findTaggedUserIdByPostId(Long postId);
}
