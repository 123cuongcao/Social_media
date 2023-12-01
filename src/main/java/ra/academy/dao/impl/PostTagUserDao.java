package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IPostTagUserDao;
import ra.academy.model.PostTagUser;
import ra.academy.model.User;

import java.util.List;

@Component
public class PostTagUserDao implements IPostTagUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PostTagUser> findAll() {
        return null;
    }

    @Override
    public PostTagUser findById(Long id) {
        return null;
    }

    @Override
    public int save(PostTagUser postTagUser) {
        return 0;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Long> findTaggedUserIdByPostId(Long postId) {
        String sql = "call proc_find_user_by_post_id(?)";
        List<Long> list = jdbcTemplate.query(sql, new Object[]{postId},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setUserId(rs.getLong("tagged_user_id"));
                    return user.getUserId();
                });
        return list;
    }
}
