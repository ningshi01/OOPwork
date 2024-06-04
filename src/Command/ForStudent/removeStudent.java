package Command.ForStudent;

import Command.Isgood;
import aggregate.Course;
import aggregate.Users;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Course.course0;
import static aggregate.Users.user0;

public class removeStudent {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String pat="\\S+";
        String wpat="removeStudent\\s+(\\S+)\\s+(\\S+)\\s*";
        Pattern r= Pattern.compile(pat);
        Pattern rr=Pattern.compile(wpat);
        Matcher m=r.matcher(command);
        Matcher mm=rr.matcher(command);
        if(mm.find()) {
            System.out.println("arguments illegal");
            return;
        }
        m.find();
        if(m.find()) {
            String id=m.group();
            if(Users.loginFlag){
                if(Users.orFlag || user0.getType()==1){
                    if(Users.selectFlag){
                        if(!Isgood.IsBadId(id)){
                            if(course0.stuList.lastIndexOf(id)!=-1){
                                arr_User.get(arr_UsersId.lastIndexOf(id)).MyCourse.remove(course0);//user的改动写入总表
                                arr_User.get(arr_UsersId.lastIndexOf(id)).MyCourseId.remove(id);
                                course0.stuList.remove(id);
                                course0.setStuNum();
                                arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                                System.out.println("remove student success");
                            }else System.out.println("user id not exist");
                        }else System.out.println("user id illegal");
                    }else System.out.println("no course selected");
                }else System.out.println("permission denied");
            }else System.out.println("not logged in");
        }else System.out.println("arguments illegal");
    }
}