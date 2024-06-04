package Command.ForCourse;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Command.Isgood;
import aggregate.*;
import aggregate.Course;

import static aggregate.Users.user0;
public class removeCourse {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId){
        String pat="\\S+";
        Pattern r= Pattern.compile(pat);
        Matcher m=r.matcher(command);
        m.find();
        if(!m.find()){
            System.out.println("arguments illegal");
            return;
        }
        String courseId=m.group();
        if(!m.find()){
            if(Users.loginFlag){
                if(user0.getType()==1) {
                    if(!Isgood.IsbadcourseId(courseId)){
                        int num=user0.MyCourseId.lastIndexOf(courseId);
                        if(num!=-1){
                            user0.MyCourse.remove(num);
                            user0.MyCourseId.remove(num);
                            arr_courses.get(arr_courseIds.lastIndexOf(courseId)).teacherList.remove(user0.getId());
                            arr_courses.get(arr_courseIds.lastIndexOf(courseId)).setTeacherNum();
                            System.out.println("remove course success");
                        }
                        else System.out.println("course id not exist");
                    }
                    else System.out.println("course id illegal");
                }
                else System.out.println("permission denied");
            }
            else System.out.println("not logged in");
        }
        else System.out.println("arguments illegal");
    }
}
