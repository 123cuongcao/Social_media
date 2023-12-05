package ra.academy.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ra.academy.dto.ChangePassword;
import ra.academy.model.User;
import ra.academy.service.IUserService;
@Component
public class ChangePasswordValidate  implements Validator {
    @Autowired
    IUserService userService ;

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePassword.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangePassword c = (ChangePassword) target;

        if(!c.getNewPassword().matches("^(?=.*[A-Z])(?=.*\\d).{5,}$")){
            errors.rejectValue("newPassword", "form-changePassword.newPassword.pattern");
        }
        if(!c.getNewPassword().equals(c.getRePassword())){
            errors.rejectValue("rePassword", "form-changePassword.rePassword.same");
        }
        if(c.getOldPassword().isEmpty()){
            errors.rejectValue("oldPassword","form-changePassword.oldPassword.enpty");
        }
        if(c.getNewPassword().isEmpty()){
            errors.rejectValue("newPassword","form-changePassword.newPassword.enpty");
        }
        if(c.getRePassword().isEmpty()){
            errors.rejectValue("rePassword","form-changePassword.rePassword.enpty");
        }
    }
}
