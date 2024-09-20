package in.kb.service;

import in.kb.binding.LoginForm;
import in.kb.binding.SignUpForm;
import in.kb.binding.UnlockForm;
import in.kb.entity.UserDtlsEntity;
import in.kb.repo.UserDtlsRepository;
import in.kb.util.EmailUtils;
import in.kb.util.PasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDtlsRepository userDtlsRepo;

    @Autowired
    private EmailUtils emailUtils;


    @Override
    public boolean signUp(SignUpForm form) {
        //Checking whether that mail have been already used or not
        UserDtlsEntity user = userDtlsRepo.findByEmail(form.getEmail());
        if(user != null){
            return false;
        }

        //TODO: Copy data from binding object to entity object
        UserDtlsEntity entity = new UserDtlsEntity();
        BeanUtils.copyProperties(form,entity);

        //TODO: generate random pwd and set to object
        String tempPwd = PasswordUtils.generateRandomPwd();
        entity.setPassword(tempPwd);

        //TODO: set account status as LOCKED
        entity.setAccountStatus("LOCKED");

        //TODO: Insert record
        userDtlsRepo.save(entity);

        //TODO: Send email to user to unlock the account
        String to = form.getEmail();
        String subject = "Unlock Your Account";
        StringBuffer body = new StringBuffer("");
        body.append("Temporary Password: "+tempPwd);
        body.append("<br/>");
        body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\">Click here to unlock the account</a>");

        emailUtils.sendEmail(to,subject,body.toString());

        return true;
    }

    @Override
    public boolean unlockAccount(UnlockForm form) {

        UserDtlsEntity entity = userDtlsRepo.findByEmail(form.getEmail());

        if(entity.getPassword().equals(form.getTempPwd())){
            entity.setPassword(form.getNewPwd());
            entity.setAccountStatus("UNLOCKED");
            userDtlsRepo.save(entity);
            return true;
        }else {
            return false;
        }
    }


    @Override
    public String login(LoginForm form) {
        UserDtlsEntity entity = userDtlsRepo.findByEmailAndPassword(form.getEmail(), form.getPassword());

        //When credential does not match
        if(entity==null){
            return "Invalid Credentials";
        }

        //When credentials match but account is in LOCKED status
        if(entity.getAccountStatus().equals("LOCKED")){
            return "Your Account Locked";
        }

        //When account is in UNLOCKED status
        return "success";
    }


    @Override
    public String forgotPwd(String email) {
        return "";
    }




}
