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
    @RequestMapping("/category")
    public String catalog() {
        return "admin/category";
    }
    @RequestMapping("/product")
    public String product() {
        return "admin/product";
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
