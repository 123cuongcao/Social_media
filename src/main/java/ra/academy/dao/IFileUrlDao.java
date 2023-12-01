package ra.academy.dao;

import ra.academy.model.Comment;
import ra.academy.model.FileUrl;

import java.util.List;

public interface IFileUrlDao extends IGenericDao<FileUrl, Long> {
    List<FileUrl> findFileUrlsByPostId(Long postId);

}
