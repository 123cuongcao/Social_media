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
import ra.academy.model.UserRelation;
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
    public String suggestFriend(Model model, HttpSession session, @PathVariable String SC) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        List<User> users = userService.findNotFriend(user.getUserId());
        List<User> list = new ArrayList<>();
        String status = null;
        switch (SC) {
            case "SF":
                users = userService.findNotFriend(user.getUserId());
                status = SC;
                break;
            case "FR":
                users = userService.findSentPendingFriendRequests(user.getUserId());
                list = userService.getRequestFriendFromUser(user.getUserId());
                status = SC;
                break;
            case "AF":
                users = userService.findAllFriend(user.getUserId());
                status = SC;
                break;
        }
        model.addAttribute("users", users);
        model.addAttribute("list", list);
        model.addAttribute("status", status);
        model.addAttribute("currenId", user.getUserId());
        return "component/default-member";
    }

    @RequestMapping("/addFriend/{id}")
    public String doDoAddFriend(@PathVariable("id") long idReceiver, HttpSession session, Model model) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail()) ).findFirst().orElse(null);
        userService.doAddFriend(user.getUserId(), idReceiver, RelationshipStatus.PENDING);
        return "redirect:/friend/SF";
    }

    @RequestMapping("/accept/{id}")
    public String doAccept(@PathVariable("id") long id, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        userService.changUserRelation(id, user.getUserId(), RelationshipStatus.FRIEND);
        return "redirect:/friend/SF";
    }

    @RequestMapping("/reject/{id}")
    public String doReject(@PathVariable("id") long id, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        userService.changUserRelation(id, user.getUserId(), RelationshipStatus.REJECTED);
        return "redirect:/friend/SF";
    }

    @RequestMapping("/cancel/{id}")
    public String doCancel(@PathVariable("id") long id, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        userService.deleteUserRelation(user.getUserId(), id);
        return "redirect:/friend/SF";
    }

    @RequestMapping("/detail/{id}")
    public String goToUserPage(@PathVariable("id") long id, Model model, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User currentLogin = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
        User user = userService.findAllUser().stream().filter(u -> u.getUserId() == id).findFirst().orElse(null);
        RelationshipStatus status ;
        model.addAttribute("user", user);

        UserRelation userRelation = userService.getUserRelation(currentLogin.getUserId(), id);
        if(userRelation == null){
            model.addAttribute("status", "");
            return "component/user-page";
        }
        status = userRelation.getStatus();
        if (userRelation.getSenderID() == currentLogin.getUserId() && status.toString().equals("PENDING")) {
            model.addAttribute("status", "CR");
        } else if (userRelation.getReceiverId() == currentLogin.getUserId() && status.toString().equals("PENDING")) {
            model.addAttribute("status", "AR");
        } else if (id == currentLogin.getUserId()) {
            model.addAttribute("status", "currentUser");
        } else {
            model.addAttribute("status", userRelation.getStatus().toString());
        }


        return "component/user-page";
    }

    @RequestMapping("/unfriend/{id}")
    public String doUnFriend(@PathVariable("id") long id ,HttpSession session){
        UserLogin userLogin = (UserLogin) session.getAttribute("user_login");
        User user = userService.findAllUser().stream().filter(u -> u.getEmail().equalsIgnoreCase(userLogin.getUserEmail())).findFirst().orElse(null);
       if( userService.deleteUserRelation(user.getUserId(), id) ==0){
           userService.deleteUserRelation(id,user.getUserId());
       }
        return "redirect:/friend/SF";
    }






}
