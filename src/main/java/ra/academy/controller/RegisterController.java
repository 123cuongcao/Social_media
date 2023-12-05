package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ra.academy.dto.UserLogin;
import ra.academy.dto.UserRegister;
import ra.academy.model.User;
import ra.academy.service.impl.UserService;
import ra.academy.validate.RegisterValidate;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterValidate registerValidate;
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String doRegister(Model model) {
        model.addAttribute("user_register", new UserRegister());
        return "component/register";
    }

    @RequestMapping(value = "/handle_register", method = RequestMethod.POST)
    String doRegister(@ModelAttribute("user_register") @Valid UserRegister userRegister, BindingResult bindingResult) {
        registerValidate.validate(userRegister, bindingResult);
        if (bindingResult.hasErrors()) {
            return "component/register";
        }

        userService.register(userRegister);

        return "redirect:/";
    }

}
