package ra.academy.model;

public class UserRelationship {
    private Long relationshipId;
    private Long userId;
    private Long friendId;
    private boolean status;

    public UserRelationship() {
    }

    public UserRelationship(Long relationshipId, Long userId, Long friendId, boolean status) {
        this.relationshipId = relationshipId;
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
