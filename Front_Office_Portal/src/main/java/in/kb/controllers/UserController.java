package in.kb.controllers;

import in.kb.binding.SignUpForm;
import in.kb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //Method responsible for loading SignUp page
    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("user", new SignUpForm());
        return "signup";
    }
    @PostMapping("/signup")
    public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model){
        //whenever pur form is submitted again we want to send the binding object back to the user
        //th:object="${user} is the binding object which will help getting the signup page after clicking the signup page
        boolean status = userService.signUp(form);

        if(status){
            model.addAttribute("succMsg","Account Created, Check your mail");
        }else {
            model.addAttribute("errMsg","Choose unique Email");
        }
        return "signup";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
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
