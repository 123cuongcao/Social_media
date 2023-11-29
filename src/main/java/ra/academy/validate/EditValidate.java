package ra.academy.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ra.academy.dto.UserEditInfor;
import ra.academy.dto.UserLogin;
import ra.academy.service.impl.UserService;

@Component
public class EditValidate implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEditInfor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEditInfor u = (UserEditInfor) target;

        if (u.getFullName().isEmpty()) {
            errors.rejectValue("fullName", "form-register.fullName.empty");
        }

        if (!u.getPhoneNumber().matches("^\\d{11}$")) {
            errors.rejectValue("phoneNumber", "form-register.phone.length");
        } else if (u.getPhoneNumber().isEmpty()) {
            errors.rejectValue("phoneNumber", "form-register.phone.empty");
        }else if (userService.checkExistByPhone(u.getPhoneNumber())) {
            errors.rejectValue("phoneNumber","form-register.phone.exist");
        }

        if (u.getDateOfBirth() == null) {
            errors.rejectValue("dateOfBirth", "form-register.birthday.empty");
        }

    }
}
