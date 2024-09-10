package in.kb.controllers;

import in.kb.binding.SignUpForm;
import in.kb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String handleSignUp(SignUpForm form, Model model){
        boolean status = userService.signUp(form);

        if(status){
            model.addAttribute("succMsg","Check your mail");
        }else {
            model.addAttribute("errorMsg","Problem occurred");
        }

        return "signup";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @GetMapping("/unlock")
    public String unlockPage() {
        return "unlock";
    }

    @GetMapping("/forgotPwd")
    public String forgotPwdPage() {
        return "forgotPwd";
    }

}
