package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IFileUrlDao;
import ra.academy.dao.IPostDao;
import ra.academy.dao.IPostTagUserDao;
import ra.academy.dao.IPostTopicDao;
import ra.academy.dto.PostRequest;
import ra.academy.dto.PostResponseAdmin;
import ra.academy.dto.PostResponseUser;
import ra.academy.model.*;
import ra.academy.service.IPostService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class PostDao implements IPostDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IPostTopicDao postTopicDao;
    @Autowired
    private IFileUrlDao fileUrlDao;
    @Autowired
    private IPostTagUserDao postTagUserDao;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private IPostService postService;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Post findById(Long id) {
        String sql = "call proc_find_post_by_id(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Post.class));
    }

    @Override
    public int save(Post post) {
        return 0;
    }

    @Override
    public void createPost(PostRequest postRequest, List<String> files) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            if (postRequest.getPostId() == null) {
                //add new
                conn.setAutoCommit(false);
                String sql1 = "call proc_create_post(?,?,?,?,?)";
                String sql2 = "insert into post_topic(post_id, topic_id) values ";
                String sql3 = "insert into post_tag_user(tagged_user_id, post_id) values ";
                String sql4 = "insert into file_url(post_id, post_url) values ";

                CallableStatement callSt1 = conn.prepareCall(sql1);
                callSt1.registerOutParameter(1, Types.BIGINT);
                callSt1.setLong(2, postRequest.getUserId());
                callSt1.setString(3, postRequest.getContent());
                callSt1.setDate(4, new Date(postRequest.getPostTime().getTime()));
                callSt1.setString(5, postRequest.getPrivacy().name());
                callSt1.executeUpdate();

                Long newPostId = callSt1.getLong(1);

                for (Long topicId : postRequest.getTopicIds()) {
                    sql2 += "(" + newPostId + "," + topicId + ")";
                    if (postRequest.getTopicIds().indexOf(topicId) < postRequest.getTopicIds().size() - 1) {
                        sql2 += ",";
                    }
                }
                CallableStatement callSt2 = conn.prepareCall(sql2);
                callSt2.executeUpdate();

                for (Long userId : postRequest.getTaggedUserIds()) {
                    sql3 += "(" + userId + "," + newPostId + ")";
                    if (postRequest.getTaggedUserIds().indexOf(userId) < postRequest.getTaggedUserIds().size() - 1) {
                        sql3 += ",";
                    }
                }
                CallableStatement callSt3 = conn.prepareCall(sql3);
                callSt3.executeUpdate();

                for (String f : files) {
                    sql4 += "(" + newPostId + "," + f + ")";
                    if (files.indexOf(f) < files.size() - 1) {
                        sql4 += ",";
                    }
                }
                CallableStatement callSt4 = conn.prepareCall(sql4);
                callSt4.executeUpdate();

                conn.commit();
            } else {
                //update

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<PostResponseAdmin> findAllPostForAdmin() {
        String sql = "call proc_find_all_post_for_admin()";
        List<PostResponseAdmin> list = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    PostResponseAdmin post = new PostResponseAdmin();
                    post.setPostId(rs.getLong("post_id"));
                    post.setUserEmail(rs.getString("email"));
                    post.setTopics(postTopicDao.findTopicsByPostId(rs.getLong("post_id")));
                    post.setPostContent(rs.getString("content"));
                    post.setUrls(fileUrlDao.findFileUrlsByPostId(rs.getLong("post_id")));
                    post.setPostTime(rs.getDate("post_time"));
                    post.setUpdateTime(rs.getDate("update_time"));
                    post.setPostPrivacy(Privacy.valueOf(rs.getString("privacy")));
                    return post;
                });
        return list;
    }

    @Override
    public List<PostResponseUser> findAllPostForUser(Long userId) {
        String sql = "call proc_find_all_post_for_user(?)";
        List<PostResponseUser> list = jdbcTemplate.query(sql, new Object[]{userId},
                (rs, rowNum) -> {
                    PostResponseUser post = new PostResponseUser();
                    post.setPostId(rs.getLong("post_id"));
                    post.setUserEmail(rs.getString("email"));
                    post.setTopics(postTopicDao.findTopicsByPostId(rs.getLong("post_id")));
                    post.setPostContent(rs.getString("content"));
                    post.setUrls(fileUrlDao.findFileUrlsByPostId(rs.getLong("post_id")));
                    post.setPostTime(rs.getDate("post_time"));
                    post.setUpdateTime(rs.getDate("update_time"));
                    post.setPostPrivacy(Privacy.valueOf(rs.getString("privacy")));
                    post.setLikeCount(rs.getInt("count_like"));
                    post.setCommentCount(rs.getInt("count_comment"));
                    post.setTaggedUserId(postTagUserDao.findTaggedUserIdByPostId(rs.getLong("post_id")));
                    return post;
                });
        return list;
    }
}