package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IUserDao;
import ra.academy.dto.ChangePassword;
import ra.academy.model.RelationshipStatus;
import ra.academy.model.User;
import ra.academy.model.UserRelation;

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
        String sql = "call findById(?)";
        return jdbcTemplate.query(sql, new Object[]{id},rs -> {
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
    public int changeUserRelation(long idSender , long idReceiver , String status) {
        String sql = "call change_user_relation(?,?,?)";
        return jdbcTemplate.update(sql,idSender,idReceiver,status);
    }

    public int addUserRelation(long idSender , long idReceiver , String status) {
        String sql = "call create_user_relation(?,?,?)";
        return jdbcTemplate.update(sql,idSender,idReceiver,status);
    }

    @Override
    public int deleteUserRelation(long idSender, long idReceiver) {
        String sql = "call deleteUserRelation(?,?)";
        return jdbcTemplate.update(sql,idSender,idReceiver);
    }

    @Override
    public int changePassword(  long idUser , String password) {
        String sql = "call changePassword(?,?)";
        return jdbcTemplate.update(sql,idUser,password);
    }

    @Override
    public UserRelation getUserRelation(long idSender, long idReceiver) {
        String sql = "call GetUserRelations(?,?)";
        return jdbcTemplate.query(sql, new Object[]{idSender,idReceiver},rs -> {
            UserRelation u = null;
            if (rs.next()) {
                u = new UserRelation();
                u.setId(rs.getLong("id"));
                u.setSenderID(rs.getLong("sender_id"));
                u.setReceiverId((rs.getLong("receiver_id")));
                u.setStatus(RelationshipStatus.valueOf(rs.getString("status")));
            }
            return u;
        });
    }

    @Override
    public List<User> findNotFriend(long currentIdUser) {
        String sql = "call get_not_friend(?)";
        List<User> list = jdbcTemplate.query(sql,new Object[]{currentIdUser},
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
    public List<User> findSentPendingFriendRequests(long currentIdUser) {
        String sql = "call GetSentPendingFriendRequests(?)";
        List<User> list = jdbcTemplate.query(sql,new Object[]{currentIdUser},
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
    public List<User> getRequestFriendFromUser(long currenIdUser) {
        String sql = "call getRequestFriendFromUser(?)";
        List<User> list = jdbcTemplate.query(sql,new Object[]{currenIdUser},
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
    public List<User> findAllFriend(long currentIdUser) {
        String sql = "call GetAllFriends(?)";
        List<User> list = jdbcTemplate.query(sql,new Object[]{currentIdUser},
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
    public int deleteById(Long id) {

        return 0;
    }

    @Override
    public User findUserByPostId(Long postId) {
        String sql = "call proc_find_user_tag_by_post_id(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{postId}, new BeanPropertyRowMapper<>(User.class));
    }
}
