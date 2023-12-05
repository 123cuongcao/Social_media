package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IUserDao;
import ra.academy.model.Post;
import ra.academy.model.User;

import java.util.List;

@Component
public class UserDao implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        String sql = "call findAllUser()";
        List<User> list = jdbcTemplate.query(sql,
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
    public User findById(Long id) {
        return null;
    }

    @Override
    public int save(User user) {
        String sql = null;
        if (user.getUserId() == null) {
            // thêm mới
            sql = "call SaveUser(?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, user.getFullName(), user.getEmail(), user.getAvatarUrl(), user.getDateOfBirth(),
                    user.getPhoneNumber(), user.getPassword());
        } else {
            sql = "call EditUser(?,?,?,?,?)";
            return jdbcTemplate.update(sql, user.getFullName(), user.getEmail(), user.getAvatarUrl(),
                    user.getDateOfBirth(), user.getPhoneNumber());
        }
    }

    public int edit(User user){
           String sql = "call EditUser(?,?,?,?,?)";
            return jdbcTemplate.update(sql, user.getFullName(), user.getEmail(), user.getAvatarUrl(),
                    user.getDateOfBirth(), user.getPhoneNumber());
    }
    @Override
    public User findByUserEmail(String email) {
        String sql = "call findByEmail(?)";
        return jdbcTemplate.query(sql, new Object[]{email},rs -> {
            User u = null;
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getLong("user_id"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setAvatarUrl(rs.getString("avatar_url"));
                u.setStatus(rs.getBoolean("status"));
                u.setDateOfBirth(rs.getDate("date_of_birth"));
                u.setCreatedAt(rs.getDate("created_at"));
                u.setUpdatedAt(rs.getDate("updated_at"));
                u.setPhoneNumber(rs.getString("phone_number"));
                u.setRole(rs.getBoolean("role"));
                u.setPassword(rs.getString("password"));
            }
            return u;
        });
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User findUserByPostId(Long postId) {
        String sql = "call proc_find_user_tag_by_post_id(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{postId}, new BeanPropertyRowMapper<>(User.class));
    }
}
