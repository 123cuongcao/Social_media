package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ra.academy.dto.ChangePassword;
import ra.academy.dto.UserLogin;
import ra.academy.service.IUserService;
import ra.academy.validate.ChangePasswordValidate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/changePassword")
public class ChangePasswordController {
    @Autowired
    ChangePasswordValidate changePasswordValidate;
    @Autowired
    IUserService userService;
    @RequestMapping("")
    public String dochangePW(Model model) {
        model.addAttribute("changePassword", new ChangePassword());
        return "component/forgot";
    }

    @RequestMapping(value = "/handle_changePassword", method = RequestMethod.POST)
    public String doChangePassword(@ModelAttribute("changePassword") @Valid ChangePassword changePassword,
                                   Model model, BindingResult bindingResult , HttpSession session) {

        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        changePasswordValidate.validate(changePassword,bindingResult);
        if(bindingResult.hasErrors()){
            return "component/forgot";
        }
        if(!userLogin.getPassword().equals(changePassword.getOldPassword()) ){
            model.addAttribute("change","Wrong old password");
            return "component/forgot";
        }
        userService.changePassword(userService.login(userLogin).getUserId(),changePassword.getNewPassword());
        return "redirect:/";
    }

}
