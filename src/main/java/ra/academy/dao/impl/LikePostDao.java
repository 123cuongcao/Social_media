package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.ILikePostDao;
import ra.academy.model.LikePost;

import java.util.List;

@Component
public class LikePostDao implements ILikePostDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<LikePost> findAll() {
        String sql = "select * from like_post";
        List<LikePost> list = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    LikePost like = new LikePost();
                    like.setLikePostId(rs.getLong("like_id"));
                    like.setPostId(rs.getLong("post_id"));
                    like.setLikedAt(rs.getDate("liked_at"));
                    like.setUserId(rs.getLong("user_id"));
                    return like;
                });
        return list;
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
    public int deleteById(Long id) {
        String sql = "delete from like_post where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public List<LikePost> findLikesByPostId(Long postId) {
        return null;
    }
}
