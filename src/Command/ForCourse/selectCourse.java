package Command.ForCourse;

import Command.Isgood;
import aggregate.Course;
import aggregate.Users;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Users.orFlag;
import static aggregate.Users.user0;
import static aggregate.Course.course0;
public class selectCourse {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId){
        String pat="\\S+";
        Pattern r= Pattern.compile(pat);
        Matcher m=r.matcher(command);
        String wpat="selectCourse\\s+(\\S+)\\s+(\\S+)\\s*";
        Pattern rr= Pattern.compile(wpat);
        Matcher mm=rr.matcher(command);
        int num;
        if(mm.find()) {
            System.out.println("arguments illegal");
            return;
        }
        m.find();
        if(m.find()) {
            String courseId = m.group();
            if (Users.loginFlag) {
                if (true/*user0.getType() == 1 || Users.orFlag*/) /*管理端+学生端*/{
                    if (!Isgood.IsbadcourseId(courseId)) {
                        if(!orFlag) num = user0.MyCourseId.lastIndexOf(courseId);
                        else  num = user0.helpCourseId.lastIndexOf(courseId);
                        if (num != -1) {
                            Users.selectFlag = true;
                            if(!orFlag) course0 = user0.MyCourse.get(num);
                            else course0 = user0.helpCourse.get(num);
                            System.out.println("select course success");
                        } else System.out.println("course id not exist");
                    } else System.out.println("course id illegal");
                }
            } else System.out.println("not logged in");
        }else System.out.println("arguments illegal");
    }
}
