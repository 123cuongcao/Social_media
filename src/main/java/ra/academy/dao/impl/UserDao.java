package ra.academy.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IUserDao;
import ra.academy.model.User;

import java.util.List;
@Component
public class UserDao implements IUserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return null;
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
                    user.getPhoneNumber(),user.getPassword() );
        } else {
            sql = "call EditUser(?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, user.getUserId(),user.getFullName(), user.getEmail(), user.getAvatarUrl(),
                    user.getDateOfBirth(), user.getPhoneNumber());
        }
    }

    @Override
    public User findByUserEmail(String email) {
        String sql = "select * from account where email = '"+email+"'";
        return jdbcTemplate.query(sql, rs -> {
            User u = null;
            if(rs.next()){
                u = new User();
                u.setUserId(rs.getLong("id"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setAvatarUrl(rs.getString("avatar_url"));
                u.setStatus(rs.getBoolean("status"));
                u.setDateOfBirth(rs.getDate("date_of_birth"));
                u.setCreatedAt(rs.getDate("created_at"));
                u.setUpdatedAt(rs.getDate("updated_at"));
                u.setPhoneNumber(rs.getString("phone_number"));
                u.setRole(rs.getBoolean("roll"));
                u.setPassword(rs.getString("password"));
            }
            return u;
        });
    }
}
