package Command.ForA_Basic;

import Command.Isgood;
import aggregate.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static aggregate.Users.user0;
public class register {
    public static void run(String command, ArrayList<Users> arr_User,ArrayList<String> arr_UsersId,ArrayList<String> arr_UsersPassword){
        String pattern_wrong = "register\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)";
        String pattern = "register\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)";
        Pattern r_wrong = Pattern.compile(pattern_wrong);
        Pattern r = Pattern.compile(pattern);
        Matcher m_wrong = r_wrong.matcher(command);
        Matcher m = r.matcher(command);
        if (m_wrong.find()) System.out.println("arguments illegal");
        else if (m.find()) {
            if(!Users.loginFlag) {
                if (Isgood.For_register(m.group(1), m.group(2), m.group(3), m.group(4), m.group(5), m.group(6))) {
                    int num = arr_UsersId.lastIndexOf(m.group(1));
                    if (num == -1) {
                        user0 = new Users(m.group(1), m.group(2), m.group(3), m.group(4), m.group(5), m.group(6));
                        arr_User.add(user0);
                        arr_UsersId.add(user0.getId());
                        arr_UsersPassword.add(user0.getPassword());
                        System.out.println("register success");
                    }
                    else  System.out.println("user id duplication");
                }
            }
            else System.out.println("already logged in");
        }
        else System.out.println("arguments illegal");
    }
}
