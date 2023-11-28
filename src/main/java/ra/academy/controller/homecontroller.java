package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class homecontroller {
    @RequestMapping("/")
    public String login(){
        return "component/default";
    }
}
