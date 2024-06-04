package Command.ForA_Basic;

import Command.Isgood;
import aggregate.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static aggregate.Users.user0;
public class printInfo{
    public static void run(String command, ArrayList<Users> arr_User,ArrayList<String> arr_UsersId) {
        String pat = "[\\S]+";
        Pattern rr=Pattern.compile(pat);
        Matcher mm= rr.matcher(command);
        mm.find();
        if(mm.find())//有参
        {
            String fid=mm.group();
            if(mm.find())
                System.out.println("arguments illegal");
            else
            {
                if(Users.loginFlag==true)
                {
                    if(user0.getType()==1)
                    {
                        if(Isgood.For_printInfo(fid)) {
                            int num = arr_UsersId.lastIndexOf(fid);
                            if (num == -1)
                                System.out.println("user id not exist");
                            else {
                                System.out.print("Name: " + arr_User.get(num).getFirstName() + " " + arr_User.get(num).getLastName() + "\n" +
                                        "ID: " + arr_User.get(num).getId() + "\n" +
                                        "Type: " + ((arr_User.get(num).getType() == 1) ? "Professor" : "Student") + "\n" +
                                        "Email: " + arr_User.get(num).getEmail() + "\n");
                            }
                        }
                    }
                    else
                        System.out.println("permission denied");
                }
                else
                    System.out.println("login first");
            }
        }
        else//无参
        {
            if (Users.loginFlag) {
                System.out.print("Name: " + user0.getFirstName() + " " + user0.getLastName() + "\n" +
                        "ID: " + user0.getId() + "\n" +
                        "Type: " + ((user0.getType() == 1) ? "Professor" : "Student") + "\n" +
                        "Email: " + user0.getEmail()+"\n");
            }
            else
                System.out.println("login first");
        }
    }
}
