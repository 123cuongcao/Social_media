package ra.academy.model;

public class UserRelation {
    private Long id;
    private Long senderID;
    private Long receiverId;
    private RelationshipStatus status;

    public UserRelation() {
    }

    public UserRelation(Long id, Long senderID, Long receiverId, RelationshipStatus status) {
        this.id = id;
        this.senderID = senderID;
        this.receiverId = receiverId;
        this.status = status;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderID() {
        return senderID;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }
}
