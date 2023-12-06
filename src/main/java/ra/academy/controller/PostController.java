package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.academy.dto.PostRequest;
import ra.academy.dto.UserLogin;
import ra.academy.service.IPostService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;

//    @GetMapping("")
//    public String post(Model model) {
//        model.addAttribute("user_post", new PostRequest());
//        return "component/default";
//    }

    @PostMapping("/handle-post")
    public String doPost(@ModelAttribute("user_post") PostRequest postRequest, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        postService.save(postRequest, userLogin.getUserEmail());
        return "redirect:/homepage";
    }
    @PostMapping("/handle-post1")
    public String doPost1(@ModelAttribute("user_post") PostRequest postRequest, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        postService.save(postRequest, userLogin.getUserEmail());
        return "redirect:/homepage/profile";
    }

    @RequestMapping("/delete_post/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return "redirect:/homepage";
    }





}
