package Command.ForA_Basic;

import Command.Isgood;
import aggregate.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static aggregate.Users.user0;
public class login {
    public static void run(String command, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId, ArrayList<String> arr_UsersPassword){
        String wrong_pattern= "login\\s+([A-Z]*[0-9]+)\\s+(\\w+)\\s+()\\w+";
        String pattern = "login\\s+([A-Z]*[0-9]+)\\s+(\\w+)";
        Pattern wrong_r=Pattern.compile(wrong_pattern);
        Pattern r = Pattern.compile(pattern);
        Matcher wrong_m = wrong_r.matcher(command);
        Matcher m = r.matcher(command);
        if(wrong_m.find())
            System.out.println("arguments illegal");
        else if(m.find()) {
            if(!Users.loginFlag) {
                if (Isgood.For_login(m.group(1), m.group(2))) {
                    int num = arr_UsersId.lastIndexOf(m.group(1));
                    if (num != -1)
                    {
                        if(Objects.equals(arr_UsersPassword.get(num), m.group(2))) {
                            user0 =arr_User.get(num);
                            Users.loginFlag=true;
                            if(arr_User.get(num).getType()==0)
                                System.out.println("Hello " + arr_User.get(num).getFirstName() + "~");
                            else
                                System.out.println("Hello Professor "+arr_User.get(num).getLastName()+"~");
                        }
                        else
                            System.out.println("wrong password");
                    }
                    else
                        System.out.println("user id not exist");
                }
            }
            else {
                System.out.println("already logged in");
            }
        }
        else
            System.out.println("arguments illegal");
    }
}
