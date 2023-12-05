package ra.academy.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserLogin {
    private String userEmail ;
    private Long userId;
    private String password;

    public UserLogin() {
    }

    public UserLogin(String userEmail, Long userId, String password) {
        this.userEmail = userEmail;
        this.userId = userId;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
