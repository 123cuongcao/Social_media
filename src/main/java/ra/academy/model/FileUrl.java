package ra.academy.model;

public class FileUrl {
    private Long postId;
    private String fileUrl;

    public FileUrl() {
    }

    public FileUrl(Long postId, String fileUrl) {
        this.postId = postId;
        this.fileUrl = fileUrl;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
