package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ra.academy.dto.PostRequest;
import ra.academy.dto.UserLogin;
import ra.academy.model.Privacy;
import ra.academy.model.User;
import ra.academy.service.IPostService;
import ra.academy.service.IPostTagUserService;
import ra.academy.service.IPostTopicService;
import ra.academy.service.IUserService;
import ra.academy.validate.LoginValidate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping
public class LoginController {
    @Autowired
    private LoginValidate loginValidate;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostTopicService postTopicService;
    @Autowired
    private IPostTagUserService postTagUserService;
    @Autowired
    private IPostService postService;


    @RequestMapping
    public String login(Model model) {
        model.addAttribute("user_login", new UserLogin());
        return "component/login";
    }

    @RequestMapping(value = "/handle-login", method = RequestMethod.POST)
    public String doLogin(HttpSession session, @ModelAttribute("user_login") @Valid UserLogin userLogin, Model model, BindingResult bindingResult) {
        loginValidate.validate(userLogin, bindingResult);
        if (bindingResult.hasErrors()) {
            return "component/login";
        }
        User user = userService.login(userLogin);
        if (user == null) {
            model.addAttribute("login_fail", "Username or password is inccorect");
            return "component/login";
        }
        if (user.isRole()) {
            session.setAttribute("user_login", userLogin);
            return "redirect:/admin/user";
        }
        session.setAttribute("user_login", userLogin);
        User user1 = userService.findAllUser().stream().filter(u->u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        model.addAttribute("list_post", postService.findAllPostForUser(user1.getUserId()));
        model.addAttribute("posting_user", user1);
        model.addAttribute("image",user1.getAvatarUrl());
        model.addAttribute("user_post", new PostRequest());
        model.addAttribute("post_topic", postTopicService.findTopic());
        model.addAttribute("tag_friend", postTagUserService.fillAllFriend());
        model.addAttribute("arrPrivacy", Privacy.values());
        return "component/default";

    }




}
