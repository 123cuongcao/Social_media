package ra.academy.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ra.academy.dto.UserLogin;

@Component
public class LoginValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserLogin.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserLogin userLogin = (UserLogin) target;
         if(userLogin.getUserEmail().isEmpty()) {
             errors.rejectValue("userEmail", "form-login.email.empty");
         }else if(!userLogin.getUserEmail().matches("^(.+)@(\\S+)$")){
            errors.rejectValue("userEmail","form-login.email.invalid");
        }

         if (userLogin.getPassword().isEmpty()) {
            errors.rejectValue("password","form-login.password.empty");
        }

    }
}


