package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ra.academy.dto.PostRequest;
import ra.academy.dto.UserLogin;
import ra.academy.model.Privacy;
import ra.academy.model.User;
import ra.academy.service.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/homepage")
public class HomePageController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostTopicService postTopicService;
    @Autowired
    private IPostTagUserService postTagUserService;
    @Autowired
    private IPostService postService;
    @Autowired
    private ILikePostService likePostService;

    @RequestMapping("")
    public String homepage(Model model, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user1 = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);

        model.addAttribute("image", user1.getAvatarUrl());
        List<User> users = userService.findNotFriend(user1.getUserId());
        List<User> list = userService.getRequestFriendFromUser(user1.getUserId());
        model.addAttribute("list",list);
        model.addAttribute("users",users);
        model.addAttribute("posting_user", user1);
        model.addAttribute("image",user1.getAvatarUrl());
        model.addAttribute("list_post", postService.findAllPostForUser(user1.getUserId()));
        model.addAttribute("user_post", new PostRequest());
        model.addAttribute("post_topic", postTopicService.findTopic());
        model.addAttribute("tag_friend", postTagUserService.fillAllFriend(user1.getUserId()));
        model.addAttribute("arrPrivacy", Privacy.values());
        model.addAttribute("like_post", likePostService.findAll());
        return "component/default";
    }

    @RequestMapping("/profile")
    public String profile(Model model, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user1 = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);

        model.addAttribute("image", user1.getAvatarUrl());
        List<User> users = userService.findNotFriend(user1.getUserId());
        List<User> list = userService.getRequestFriendFromUser(user1.getUserId());
        model.addAttribute("list",list);
        model.addAttribute("users",users);
        model.addAttribute("posting_user", user1);
        model.addAttribute("image",user1.getAvatarUrl());
        model.addAttribute("list_post", postService.findAllPostForUser(user1.getUserId()));
        model.addAttribute("user_post", new PostRequest());
        model.addAttribute("post_topic", postTopicService.findTopic());
        model.addAttribute("tag_friend", postTagUserService.fillAllFriend(user1.getUserId()));
        model.addAttribute("arrPrivacy", Privacy.values());
        model.addAttribute("like_post", likePostService.findAll());
        return "component/author-page";
    }

    @RequestMapping("/setting")
    public String setting(HttpSession session, Model model) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        model.addAttribute("image", user.getAvatarUrl());
        return  "component/default-settings";
    }

    @RequestMapping(value = "/default")
    public String directHome(HttpSession session, Model model) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        model.addAttribute("image", user.getAvatarUrl());
        return "component/default";
    }

    @RequestMapping(value = "/poststory")
    public String addstory() {

        return "component/postStory";
    }




}
