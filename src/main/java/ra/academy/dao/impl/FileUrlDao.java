package ra.academy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ra.academy.dao.IFileUrlDao;
import ra.academy.model.FileUrl;

import java.util.List;

@Component
public class FileUrlDao implements IFileUrlDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<FileUrl> findFileUrlsByPostId(Long postId) {
        String sql = "call proc_find_post_url_by_post_id(?)";
        List<FileUrl> list = jdbcTemplate.query(sql, new Object[]{postId},
                (rs, rowNum) -> {
                    FileUrl url = new FileUrl();
                    url.setUrlId(rs.getLong("file_url_id"));
                    url.setPostId(rs.getLong("post_id"));
                    url.setFileUrl(rs.getString("post_url"));
                    return url;
                });
        return list;
    }


    @Override
    public List<FileUrl> findAll() {
        return null;
    }

    @Override
    public FileUrl findById(Long id) {
        return null;
    }

    @Override
    public int save(FileUrl fileUrl) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {

        return 0;
    }
}
