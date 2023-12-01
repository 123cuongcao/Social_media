package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("component/defautl")
public class HomePageController {
    @RequestMapping("/setting")
    public String setting() {
        return "component/default-settings";
    }

    @RequestMapping("/poststory")
    public String addstory() {
        return "component/postStory";
    }
}
