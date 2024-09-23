package in.kb.controllers;

import in.kb.binding.LoginForm;
import in.kb.binding.SignUpForm;
import in.kb.binding.UnlockForm;
import in.kb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/unlock")
    public String unlockPage(@RequestParam String email, Model model) {
        //@RequestParam -> To capture the data from the query parameter
        UnlockForm unlockFormObj = new UnlockForm();
        unlockFormObj.setEmail(email);
        //This will show for which email the password is being UnLocked
        model.addAttribute("unlock",unlockFormObj);

        return "unlock";
    }


    @PostMapping("/unlock")
    public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock, Model model){
        System.out.println(unlock);

        if(unlock.getNewPwd().equals(unlock.getConfirmPwd())){
            boolean status = userService.unlockAccount(unlock);
            if(status){
                model.addAttribute("succMsg","Your account unlocked successfully");
            }else {
                model.addAttribute("errMsg","Given tempPwd is incorrect, check your mail.");
            }
        }else{
            model.addAttribute("errMsg","New pwd and pwd should be same");
        }
        return "unlock";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        //Login Page will be loaded
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {

        String status = userService.login(loginForm);

        //If data which are accepted from the logInForm are correct we will get the success message-
        // It will redirect us to dashboard
        if(status.contains("success")){
            //redirect request to DashBoard method
            //return "dashboard";
            return "redirect:/dashboard";
        }

        //If status is not success we will get the reason of why it is un-success
        model.addAttribute("errMsg", status);

        return "login";
    }

    @GetMapping("/forgotPwd")
    public String forgotPwdPage() {
        return "forgotPwd";
    }

    @PostMapping("/forgotPwd")
    public String forgotPwd(@RequestParam("email") String email, Model model) {
        System.out.println(email);

        //TODO: Logic
        boolean status = userService.forgotPwd(email);
        if(status){
            //Send success message
            model.addAttribute("succMsg", "Password sent to your email");
        }else {
            //Send error message
            model.addAttribute("errMsg","Invalid Email");
        }
        return "forgotPwd";
    }

}
