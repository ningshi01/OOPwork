package Command.ForWare;

import aggregate.Course;
import aggregate.Users;
import aggregate.Ware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;

public class listWare {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String pat = "\\S+";
        Pattern r = Pattern.compile(pat);
        Matcher m = r.matcher(command);
        m.find();
        if (m.find()) {
            System.out.println("arguments illegal");
            return;
        }

        Comparator cmp3=new Comparator<Ware>() {
            @Override
            public int compare(Ware p1, Ware p2) {
                return p1.getWareId().compareTo(p2.getWareId());
            }
        };

        if (Users.loginFlag) {
            if (true/*Users.orFlag || user0.getType()==1*/)/*管理端+学生端*/ {
                if(Users.selectFlag){
                    if(course0.wareList.size()!=0) {
                        Collections.sort(course0.wareIdList);
                        Collections.sort(course0.wareList, cmp3);
                        course0.wareList.forEach(e -> {
                            System.out.println("[ID:" + e.getWareId() + "] [Name:" + e.getWare() + "]");
                        });
                    } else System.out.println("total 0 ware");
                }else System.out.println("no course selected");
            }
        } else System.out.println("not logged in");
    }
}
