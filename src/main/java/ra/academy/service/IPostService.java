package ra.academy.service;

import ra.academy.dto.PostRequest;
import ra.academy.dto.PostResponseAdmin;
import ra.academy.dto.PostResponseUser;

import java.util.List;

public interface IPostService {
    List<PostResponseAdmin> findAllPostForAdmin();
    List<PostResponseUser> findAllPostForUser(Long userId);
    void save(PostRequest postRequest, String email);
    void deleteById(Long id);
    void changePostStatus(Long id);
    List<PostResponseAdmin> findAllPostByContent(int page, int size, String name);
    long getTotalPage(int size,String search);

}
