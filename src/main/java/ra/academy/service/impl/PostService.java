package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.IPostDao;
import ra.academy.dto.PostRequest;
import ra.academy.dto.PostResponseAdmin;
import ra.academy.dto.PostResponseUser;
import ra.academy.service.IPostService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


@Service
public class PostService implements IPostService {
    @Autowired
    private IPostDao postDao;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UploadService uploadService;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<PostResponseAdmin> findAllPostForAdmin() {
        return postDao.findAllPostForAdmin();
    }

    @Override
    public List<PostResponseUser> findAllPostForUser(Long userId) {
        return postDao.findAllPostForUser(userId);
    }

    @Override
    public void save(PostRequest postRequest) {
        List<String> files = uploadService.uploadFileToServer(postRequest.getFiles());
        postDao.createPost(postRequest, files);
    }

    @Override
    public void deleteById(Long id) {

    }
}
