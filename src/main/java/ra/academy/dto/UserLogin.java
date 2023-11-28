package ra.academy.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserLogin {
    @NotBlank(message = "Not empty")
    @Pattern(regexp = "^(.+)@(\\S+)$",message = "Email is invalid !")
    private String userEmail ;
    @NotBlank(message = "Not empty")
    private String password;

    public UserLogin(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public UserLogin() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
