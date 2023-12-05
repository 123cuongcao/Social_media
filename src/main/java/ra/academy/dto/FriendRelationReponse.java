package ra.academy.dto;

import org.springframework.validation.annotation.Validated;
import ra.academy.model.RelationshipStatus;
@Validated
public class FriendRelationReponse {
    private Long id;
    private String userName;
    private String email;
    private RelationshipStatus status;


    public FriendRelationReponse(Long id, String userName, String email, RelationshipStatus status) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.status = status;
    }

    public FriendRelationReponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }
}
