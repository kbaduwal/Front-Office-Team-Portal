package in.kb.service;

import in.kb.binding.LoginForm;
import in.kb.binding.SignUpForm;
import in.kb.binding.UnlockForm;

public interface UserService {
    public boolean signUp(SignUpForm form);

    public boolean unlockAccount(UnlockForm form);

    public String login(LoginForm form);


    public String forgotPwd(String email);
}
