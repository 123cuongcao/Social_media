package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.academy.service.IPostService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IPostService postService;
    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/post")
    public String post(Model model) {
        model.addAttribute("list_post", postService.findAllPostForAdmin());
        return "admin/post";
    }

    @RequestMapping("/ads")
    public String product() {
        return "admin/ads";
    }

    @RequestMapping("/user")
    public String user() {
        return "admin/user";
    }

    @RequestMapping("/addirect")
    public String direct() {
        return "component/default";
    }

}
