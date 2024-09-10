package in.kb.util;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordUtils {
    public static String generateRandomPwd(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String pwd = RandomStringUtils.random( 6, characters );
        return pwd;
    }
}
