package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("component/defautl")
public class HomePageController {
    @RequestMapping("/setting")
    public String setting() {
        return "component/default-settings";
    }

    @RequestMapping(value = "/poststory", method = RequestMethod.POST)
    public String addstory() {
        return "component/postStory";
    }

//    @RequestMapping("/addNewFile")
//    public String doReel() {}
}
