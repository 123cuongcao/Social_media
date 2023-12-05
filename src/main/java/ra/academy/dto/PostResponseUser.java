package ra.academy.dto;

import ra.academy.model.*;

import java.util.Date;
import java.util.List;

public class PostResponseUser {
    private Long postId;
    private String userEmail;
    private List<Topic> topics;
    private String postContent;
    private List<FileUrl> urls;
    private Date postTime;
    private Date updateTime;
    private Privacy postPrivacy;
    private List<Long> taggedUserId;
    private boolean status;

    public PostResponseUser() {
    }

    public PostResponseUser(Long postId, String userEmail, List<Topic> topics, String postContent, List<FileUrl> urls, Date postTime, Date updateTime, Privacy postPrivacy, List<Long> taggedUserId, boolean status) {
        this.postId = postId;
        this.userEmail = userEmail;
        this.topics = topics;
        this.postContent = postContent;
        this.urls = urls;
        this.postTime = postTime;
        this.updateTime = updateTime;
        this.postPrivacy = postPrivacy;
        this.taggedUserId = taggedUserId;
        this.status = status;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public List<FileUrl> getUrls() {
        return urls;
    }

    public void setUrls(List<FileUrl> urls) {
        this.urls = urls;
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

    public Privacy getPostPrivacy() {
        return postPrivacy;
    }

    public void setPostPrivacy(Privacy postPrivacy) {
        this.postPrivacy = postPrivacy;
    }

    public List<Long> getTaggedUserId() {
        return taggedUserId;
    }

    public void setTaggedUserId(List<Long> taggedUserId) {
        this.taggedUserId = taggedUserId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
