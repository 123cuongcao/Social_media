package ra.academy.dto;

import org.springframework.web.multipart.MultipartFile;
import ra.academy.model.*;

import java.util.Date;
import java.util.List;

public class PostRequest {
    private Long postId;
    private Long userId;
    private List<Long> topicIds;
    private String content;
    private List<MultipartFile> files;
    private Date postTime;
    private Date updateTime;
    private Privacy privacy;
    private List<Long> taggedUserIds;
    public PostRequest() {
    }

    public Long getPostId() {
        return postId;
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

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public List<Long> getTaggedUserIds() {
        return taggedUserIds;
    }

    public void setTaggedUserIds(List<Long> taggedUserIds) {
        this.taggedUserIds = taggedUserIds;
    }
}
