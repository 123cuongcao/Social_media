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
    public List<User> findAllFriendId(Long userId) {
        String sql = "call GetAllFriends(?)";
        List<User> list = jdbcTemplate.query(sql,new Object[]{userId},
                (rs, rowNum) -> {
                    User u = new User();
                    u.setUserId(rs.getLong("user_id"));
                    u.setFullName(rs.getString("full_name"));
                    u.setEmail(rs.getString("email"));
                    u.setPhoneNumber(rs.getString("phone_number"));
                    u.setDateOfBirth(rs.getDate("date_of_birth"));
                    u.setAvatarUrl(rs.getString("avatar_url"));
                    u.setStatus(rs.getBoolean("status"));
                    u.setRole(rs.getBoolean("role"));
                    u.setUpdatedAt(rs.getDate("updated_at"));
                    u.setPassword(rs.getString("password"));
                    u.setCreatedAt(rs.getDate("created_at"));
                    return u;
                });
        return list;
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
    public int deleteById(Long id) {

        return 0;
    }

    @Override
    public List<Long> findTaggedUserIdByPostId(Long postId) {
        String sql = "call proc_find_user_tag_by_post_id(?)";
        List<Long> list = jdbcTemplate.query(sql, new Object[]{postId},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setUserId(rs.getLong("tagged_user_id"));
                    return user.getUserId();
                });
        return list;
    }
}
