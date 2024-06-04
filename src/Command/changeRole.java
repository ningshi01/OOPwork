package Command;

import aggregate.Course;
import aggregate.Users;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Users.user0;

public class changeRole {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String pat="\\S+";
        Pattern r= Pattern.compile(pat);
        Matcher m=r.matcher(command);
        m.find();
        if(m.find()) {
            System.out.println("arguments illegal");
            return;
        }
        if(Users.loginFlag){
            if(user0.getStu_type()>0){
                if(Users.orFlag==false) {
                    Users.orFlag = true;
                    System.out.println("change into Assistant success");
                    Users.selectFlag=false;
                }
                else{
                    Users.orFlag = false;
                    System.out.println("change into Student success");
                    Users.selectFlag=false;
                }
            } else System.out.println("permission denied");
        } else System.out.println("not logged in");
    }
}