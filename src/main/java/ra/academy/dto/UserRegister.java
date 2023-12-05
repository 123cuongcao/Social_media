package ra.academy.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.util.Date;
@Validated
public class UserRegister {

    private String fullName;
    private String rePassword;
    private String email;
    private String phoneNumber;
    private MultipartFile avatarUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String password;

    public UserRegister() {

    }

    public UserRegister(String fullName, String rePassword, String email, String phoneNumber, MultipartFile avatarUrl, Date dateOfBirth, String password) {
        this.fullName = fullName;
        this.rePassword = rePassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MultipartFile getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(MultipartFile avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
