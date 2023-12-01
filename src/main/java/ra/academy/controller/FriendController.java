package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.academy.dto.UserLogin;
import ra.academy.model.RelationshipStatus;
import ra.academy.model.User;
import ra.academy.service.IAdminService;
import ra.academy.service.IUserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IUserService userService;
    @RequestMapping("/{SC}")
    public String suggestFriend( Model model, HttpSession session, @PathVariable String SC ){
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u->u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        List<User> users =  userService.findNotFriend(user.getUserId());
        List<User> list = new ArrayList<>();
        String status = null ;
        switch (SC){
            case "SF":
                users = userService.findNotFriend(user.getUserId());
                status = SC;
                break;
            case "FR" :
                users =userService.findSentPendingFriendRequests(user.getUserId());
                list = userService.getRequestFriendFromUser(user.getUserId());
                status = SC;
                break;
            case "AF":
                users = userService.findAllFriend(user.getUserId());
                status = SC;
                break;
        }
        model.addAttribute("users", users);
        model.addAttribute("list",list);
        model.addAttribute("status",status);
        model.addAttribute("currenId",user.getUserId());
        return "component/default-member";
    }
    @RequestMapping("/addFriend/{id}")
    public String doDoAddFriend(@PathVariable("id") long idReceiver, HttpSession session ,Model model){
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u->u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        userService.doAddFriend(user.getUserId(),idReceiver, RelationshipStatus.PENDING);
        return "redirect:/friend";
    }


}
