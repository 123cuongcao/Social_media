package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.academy.dto.PostResponseAdmin;
import ra.academy.dto.UserLogin;
import ra.academy.model.Post;
import ra.academy.model.User;
import ra.academy.service.IAdminService;
import ra.academy.service.IUserService;

import javax.servlet.http.HttpSession;

import java.util.List;

import ra.academy.service.IPostService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;
    @Autowired
    private IAdminService adminService;

    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/ads")
    public String product() {
        return "admin/ads";
    }

    @RequestMapping("/user")
    public String user(Model model, HttpSession session,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "2") int size,
                       @RequestParam(value = "nameFind", defaultValue = "") String nameToFind
    ) {
        List<User> list = adminService.findAllByName(page,size,nameToFind);
        model.addAttribute("users", list);
        model.addAttribute("current_page", page);
        model.addAttribute("size", size);
        model.addAttribute("total_page", new int[(int) adminService.getTotalPage(size,nameToFind)]);
        model.addAttribute("nameToFind",nameToFind);
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        model.addAttribute("user", user);
        return "admin/user";
    }
    @RequestMapping("/post")
    public String post(Model model, HttpSession session,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "2") int size,
                       @RequestParam(value = "contentFind", defaultValue = "") String contentToFind
    ) {
        List<PostResponseAdmin> list = postService.findAllPostByContent(page,size,contentToFind);
        model.addAttribute("posts", list);
        model.addAttribute("p_page", page);
        model.addAttribute("p_size", size);
        model.addAttribute("p_total_page", new int[(int) postService.getTotalPage(size,contentToFind)]);
        model.addAttribute("contentToFind",contentToFind);
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        model.addAttribute("user", user);
        return "admin/post";
    }

    @RequestMapping("/addirect")
    public String direct() {
        return "component/default";
    }


    @RequestMapping("/doBanOrUnban/{id}")
    public String doBan(@PathVariable Long id) {
        adminService.banUserOrUnban(id);
        return "redirect:/admin/user";
    }


    @RequestMapping("/ban_unban_post/{id}")
    public String banPost(@PathVariable Long id) {
        postService.changePostStatus(id);
        return "redirect:/admin/post";
    }

}
