package ImageHoster.utils;

import java.util.regex.*;

public class utils {

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*?[A-Z,a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{3,}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
