package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.academy.dao.IReelDao;
import ra.academy.dto.UserLogin;
import ra.academy.model.Reel;
import ra.academy.model.User;
import ra.academy.service.IReelService;
import ra.academy.service.IUserService;
import ra.academy.service.impl.UploadService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@RequestMapping("/")
public class ReelContronller {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IReelService reelService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private HttpSession session;


    @RequestMapping("/handle_addStory")
    public String addStory(Reel newR, HttpSession session, Model m) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getUserId().equals(userLogin.getUserId())).findFirst().orElse(null);
        return "component/postStory";
    }

    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("submitimg") MultipartFile m, HttpSession session,Model model) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equals(userLogin.getUserEmail())).findFirst().orElse(null);
        long id = reelService.addStory(user.getUserId(), uploadService.uploadFile(m));
        Reel reel = reelService.getReelById(id);
        model.addAttribute("reel",reel);
        return "component/default-stories";
    }


    @RequestMapping("/home")
    public String homepage() {
        return "redirect:/component/defautl/";
    }

}
