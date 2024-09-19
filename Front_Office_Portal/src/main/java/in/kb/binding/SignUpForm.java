package in.kb.binding;
//Entity class variable and Binding class variable name should be same
import lombok.Data;

@Data
public class SignUpForm {
    private String name;
    private String email;
    private String phoneNumber;
}
