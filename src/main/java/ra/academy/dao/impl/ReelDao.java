package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IReelDao;
import ra.academy.model.Reel;
import ra.academy.model.User;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Component
public class ReelDao implements IReelDao {
    @Autowired
    private DataSource dataSource;


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
    public int deleteById(Long id) {

        return 0;
    }

    @Override
    public Reel findByUserEmail(String email) {
        String sql = "call proc_find_post_by_id(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(Reel.class));
    }

    @Override
    public Long addStory(Long userId, String upload_url) {
        Connection conn = null;
        long idReel;
        try {
            conn = dataSource.getConnection();
            String sql1 = "call addReel(?,?,?)";
            CallableStatement callSt1 = conn.prepareCall(sql1);
            callSt1.setLong(1, userId);
            callSt1.setString(2, upload_url);
            callSt1.registerOutParameter(3, Types.BIGINT);

            callSt1.executeUpdate();

            idReel = callSt1.getLong(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return idReel;
    }

    @Override
    public Reel getReel(long idReel) {
        String sql = "call getReel(?)";
        return jdbcTemplate.query(sql, new Object[]{idReel}, rs -> {
            Reel u = null;
            if (rs.next()) {
                u = new Reel();
                u.setReelId(rs.getLong("reel_id"));
                u.setUpload_url(rs.getString("reel_url"));
                u.setUserId(rs.getLong("user_id"));
            }
            return u;
        });
    }
}