package ra.academy.model;

public class FileUrl {
    private Long urlId;
    private Long postId;
    private String fileUrl;

    public FileUrl() {
    }

    public FileUrl(Long urlId, Long postId, String fileUrl) {
        this.urlId = urlId;
        this.postId = postId;
        this.fileUrl = fileUrl;
    }

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
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
