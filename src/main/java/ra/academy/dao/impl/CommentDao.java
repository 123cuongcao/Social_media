package ra.academy.dao.impl;

import ra.academy.dao.ICommentDao;
import ra.academy.model.Comment;

import java.util.List;

public class CommentDao implements ICommentDao {
    @Override
    public List<Comment> findCommentsByPostId(Long postId) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return null;
    }

    @Override
    public int save(Comment comment) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {

        return 0;
    }
}
