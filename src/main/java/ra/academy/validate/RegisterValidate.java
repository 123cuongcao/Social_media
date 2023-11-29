package ra.academy.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ra.academy.dao.IUserDao;
import ra.academy.dto.UserLogin;
import ra.academy.dto.UserRegister;
import ra.academy.service.IUserService;

@Component
public class RegisterValidate implements Validator {

    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegister.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegister userRegister = (UserRegister) target;
        if (userRegister.getEmail().isEmpty()) {
            errors.rejectValue("email", "form-register.email.empty");
        } else if (!userRegister.getEmail().matches("^(.+)@(\\S+)$")) {
            errors.rejectValue("email", "form-register.email.invalid");
        } else if (userService.checkExistByEmail(userRegister.getEmail())) {
            errors.rejectValue("email", "form-register.email.exist");
        }

        if (userRegister.getFullName().isEmpty()) {
            errors.rejectValue("email", "form-register.fullName.empty");
        }

        if (userRegister.getPassword().isEmpty()) {
            errors.rejectValue("password", "form-register.password.empty");
        } else if (!userRegister.getPassword().matches("^(?=.*[A-Z])(?=.*\\d).{5,}$")) {
            errors.rejectValue("password", "form-register.password.pattern");
        }

        if (!userRegister.getRePassword().equals(userRegister.getPassword())) {
            errors.rejectValue("rePassword", "form-register.rePassword.error");
        }

        if (!userRegister.getPhoneNumber().matches("^\\d{11}$")) {
            errors.rejectValue("phoneNumber", "form-register.phone.length");
        } else if (userRegister.getPhoneNumber().isEmpty()) {
            errors.rejectValue("phoneNumber", "form-register.phone.empty");
        } else if (userService.checkExistByPhone(userRegister.getPhoneNumber())) {
            errors.rejectValue("phoneNumber","form-register.phone.exist");
        }
        if (userRegister.getDateOfBirth() == null) {
            errors.rejectValue("dateOfBirth", "form-register.birthday.empty");
        }

    }
}
