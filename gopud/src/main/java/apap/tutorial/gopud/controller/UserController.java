package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user) {
        userService.addUser(user);
        return "home";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, params = {"old-password", "new-password", "confirm-new-password"})
    private String changePassword(HttpServletRequest req, Model model) {
        UserModel userModel = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        String oldPassword = req.getParameter("old-password");
        String newPassword = req.getParameter("new-password");
        String confirmNewPassword = req.getParameter("confirm-new-password");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(oldPassword, userModel.getPassword());

        boolean changePasswordFlag = false;
        if (matches && newPassword.equals(confirmNewPassword)) {
            changePasswordFlag = true;
        }

        model.addAttribute("changePasswordFlag", changePasswordFlag);

        if (changePasswordFlag) {
            userService.changePassword(userModel, newPassword);
            model.addAttribute("user", userModel);
        }
        return "change-password";
    }
}
