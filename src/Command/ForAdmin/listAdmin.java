package Command.ForAdmin;

import aggregate.Course;
import aggregate.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.user0;

public class listAdmin {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String pat="\\S+";
        Pattern r= Pattern.compile(pat);
        Matcher m=r.matcher(command);
        m.find();
        if(m.find()) {
            System.out.println("arguments illegal");
            return;
        }
        Comparator cmp2=new Comparator<Users>() {
            @Override
            public int compare(Users o1, Users o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
        if(Users.loginFlag){
            if(user0.getType()==1 || Users.orFlag) {
                if(Users.selectFlag){
                    Collections.sort(course0.adminIdList);
                    Collections.sort(course0.adminList,cmp2);
                    course0.adminList.forEach(e->{
                        System.out.println("[ID:"+e.getId()+"] [Name:"+e.getLastName()+" "+e.getFirstName()+"] [Type:"+((e.getType()==1)?"Professor":"Assistant")+"] [Email:"+e.getEmail()+"]");
                    });
                } else System.out.println("no course selected");
            }
            else{
                if(Users.selectFlag){
                    Collections.sort(course0.adminIdList);
                    Collections.sort(course0.adminList,cmp2);
                    course0.adminList.forEach(e->{
                        System.out.println("[Name:"+e.getLastName()+" "+e.getFirstName()+"] [Type:"+((e.getType()==1)?"Professor":"Assistant")+"] [Email:"+e.getEmail()+"]");
                    });
                } else System.out.println("no course selected");
            }
        } else System.out.println("not logged in");
    }
}
