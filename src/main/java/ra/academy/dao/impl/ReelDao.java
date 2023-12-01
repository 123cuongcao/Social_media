package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IReelDao;
import ra.academy.model.Reel;
import ra.academy.model.User;

import java.util.List;

@Component
public class ReelDao implements IReelDao {
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
                    u.setEmail(rs.getString("email"));
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
        return 0;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User findByUserEmail(String email) {
        String sql = "call findByEmail(?)";

        return null;
    };

}
