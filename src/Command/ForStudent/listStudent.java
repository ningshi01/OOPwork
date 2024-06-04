package Command.ForStudent;

import aggregate.Course;
import aggregate.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.user0;

public class listStudent {
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
            if(Users.orFlag || user0.getType()==1){
                if(Users.selectFlag){
                    Collections.sort(course0.stuList);
                    course0.stuList.forEach(e->{
                        System.out.println("[ID:"+e+"] [Name:"+arr_User.get(arr_UsersId.lastIndexOf(e)).getLastName()+" "+arr_User.get(arr_UsersId.lastIndexOf(e)).getFirstName()+"] [Email:"+arr_User.get(arr_UsersId.lastIndexOf(e)).getEmail()+"]");
                    });
                }else System.out.println("no course selected");
            }else System.out.println("permission denied");
        }else System.out.println("not logged in");
    }
}