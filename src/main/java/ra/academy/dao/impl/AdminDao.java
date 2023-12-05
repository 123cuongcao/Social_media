package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IAdminDao;
import ra.academy.dao.IUserDao;
import ra.academy.model.User;

import java.util.List;

@Component
public class AdminDao implements IAdminDao {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteById(Long id) {

        return 0;
    }

    @Override
    public int banOrUnbanUser(long idUser) {
        String sql = "CALL banOrUnban(?)";
        return jdbcTemplate.update(sql, idUser);
    }

    @Override
    public List<User> findAllP(int limit, int offset) {
        String sql = "CALL pagination(?,?) ";
        List<User> list = jdbcTemplate.query(sql, new Object[]{limit, offset},
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
    public List<User> findAllByName(int limit, int offset, String name) {
        String sql = "CALL findUserByName(?,?,?) ";
        List<User> list = jdbcTemplate.query(sql, new Object[]{limit, offset, name},
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
    public List<User> findAll() {
        return userDao.findAll();
    }


    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public int save(User user) {
        return 0;
    }

}
