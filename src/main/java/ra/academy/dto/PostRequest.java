package ra.academy.dto;

import org.springframework.web.multipart.MultipartFile;
import ra.academy.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostRequest {
    private Long postId;
    private Long userId;
    private List<Long> topicIds = new ArrayList<>();
    private String content;
    private List<MultipartFile> files;
    private String privacy;
    private List<Long> taggedUserIds = new ArrayList<>();
    public PostRequest() {
    }

    public Long getPostId() {
        return postId;
    }

    public PostRequest(Long postId, Long userId, List<Long> topicIds, String content, List<MultipartFile> files, String privacy, List<Long> taggedUserIds) {
        this.postId = postId;
        this.userId = userId;
        this.topicIds = topicIds;
        this.content = content;
        this.files = files;
        this.privacy = privacy;
        this.taggedUserIds = taggedUserIds;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<Long> topicIds) {
        this.topicIds = topicIds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public List<Long> getTaggedUserIds() {
        return taggedUserIds;
    }

    public void setTaggedUserIds(List<Long> taggedUserIds) {
        this.taggedUserIds = taggedUserIds;
    }
}
