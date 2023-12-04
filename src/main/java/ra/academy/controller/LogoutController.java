package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @RequestMapping("/doLogout")
    String logout(HttpSession session) {
        session.removeAttribute("user_login");
        return "redirect:/";
    }
}
