package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ra.academy.dto.UserLogin;
import ra.academy.model.User;
import ra.academy.service.IUserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/homepage")
public class HomePageController {
    @Autowired
    private IUserService userService;

    @RequestMapping("")
    public String homepage(Model model, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user1 = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);

        model.addAttribute("image", user1.getAvatarUrl());
        List<User> users = userService.findNotFriend(user1.getUserId());
        List<User> list = userService.getRequestFriendFromUser(user1.getUserId());
        model.addAttribute("list",list);
        model.addAttribute("users",users);
        return "component/default";
    }

    @RequestMapping("/setting")
    public String setting(HttpSession session, Model model) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        model.addAttribute("image", user.getAvatarUrl());
        return "component/default-settings";
    }

    @RequestMapping(value = "/poststory")
    public String addstory() {
        return "component/postStory";
    }





//    @RequestMapping("/addNewFile")
//    public String doReel() {}
}
