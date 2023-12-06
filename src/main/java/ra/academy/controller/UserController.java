package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.academy.dto.UserEditInfor;
import ra.academy.dto.UserLogin;
import ra.academy.model.User;
import ra.academy.service.impl.UserService;
import ra.academy.validate.EditValidate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/homepage")
public class UserController {
    @Autowired
    private EditValidate editValidate;
    @Autowired
    private UserService userService;

    @RequestMapping("/information")
    public String information(HttpSession session, Model model) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);

        final UserEditInfor userByEmailToEdit = userService.findUserByEmailToEdit(userLogin.getUserEmail());
        model.addAttribute("user_edit", userByEmailToEdit);
        model.addAttribute("image", user.getAvatarUrl());
        return "component/account-information";
    }

    @RequestMapping(value = "/handle_edit", method = RequestMethod.POST)
    public String doEdit(@ModelAttribute("user_edit") @Valid UserEditInfor userEditInfor, BindingResult bindingResult) {

        editValidate.validate(userEditInfor, bindingResult);
        if (bindingResult.hasErrors()) {

            return "component/account-information";
        }

        userService.edit(userEditInfor);
        return "redirect:/homepage/information";
    }
    @RequestMapping(value = "/homa")
    public String directHome(HttpSession session, Model model) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        model.addAttribute("image", user.getAvatarUrl());
        return "component/default";
    }
}
