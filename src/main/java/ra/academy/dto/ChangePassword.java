package ra.academy.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ChangePassword {

    private String oldPassword;


    private String rePassword;


    private String newPassword;

    public ChangePassword() {
    }

    public ChangePassword(String oldPassword, String rePassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.rePassword = rePassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}
