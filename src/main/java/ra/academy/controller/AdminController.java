package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/post")
    public String post() {
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
