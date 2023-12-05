package ra.academy.dao;

import ra.academy.dto.PostRequest;
import ra.academy.dto.PostResponseAdmin;
import ra.academy.dto.PostResponseUser;
import ra.academy.model.Post;
import ra.academy.model.Privacy;
import ra.academy.model.User;

import java.util.List;

public interface IPostDao extends IGenericDao<Post, Long> {
    List<PostResponseAdmin> findAllPostForAdmin();
    List<PostResponseUser> findAllPostForUser(Long userId);
    void createPost(PostRequest postRequest, List<String> files, String email, Privacy privacy);

    void changePostStatus(Long id);
    List<PostResponseAdmin> findAllPostByContent(int limit, int offset, String content);
}
