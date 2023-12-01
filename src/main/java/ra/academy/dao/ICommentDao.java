package ra.academy.dao;

import ra.academy.model.Comment;

import java.util.List;

public interface ICommentDao extends IGenericDao<Comment, Long> {
    List<Comment> findCommentsByPostId(Long postId);
}
