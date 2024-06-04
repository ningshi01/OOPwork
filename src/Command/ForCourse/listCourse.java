package Command.ForCourse;

import aggregate.Users;
import aggregate.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Users.*;

public class listCourse {

    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId){
        String pat="\\S+";
        Pattern r= Pattern.compile(pat);
        Matcher m=r.matcher(command);
        m.find();
        if(m.find()) {
            System.out.println("arguments illegal");
            return;
        }
        Comparator cmp1=new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getCourseId().compareTo(o2.getCourseId());
            }
        };
        if(Users.loginFlag){
            if(user0.getType()==1 ) /*老师端*/{
                if(user0.MyCourse.size()!=0) {
                    Collections.sort(user0.MyCourseId);
                    Collections.sort(user0.MyCourse,cmp1);
                    user0.MyCourse.forEach((e)->{
                        System.out.println("[ID:"+e.getCourseId()+"] [Name:"+e.getCourse()+"] [TeacherNum:"+e.getTeacherNum()+"] [AssistantNum:"+e.getAssistantNum()+"] [StudentNum:"+e.getStuNum()+"]");
                    });
                }
                else System.out.println("course not exist");
            }
            else{
                if(orFlag)/*助教端*/{
                    if(user0.helpCourse.size()!=0) {
                        Collections.sort(user0.helpCourseId);
                        Collections.sort(user0.helpCourse,cmp1);
                        user0.helpCourse.forEach((e)->{
                            System.out.println("[ID:"+e.getCourseId()+"] [Name:"+e.getCourse()+"] [TeacherNum:"+e.getTeacherNum()+"] [AssistantNum:"+e.getAssistantNum()+"] [StudentNum:"+e.getStuNum()+"]");
                        });
                    }
                    else System.out.println("course not exist");
                }
                else/*学生端*/{
                    if(user0.MyCourse.size()!=0) {
                        Collections.sort(user0.MyCourseId);
                        Collections.sort(user0.MyCourse,cmp1);
                        user0.MyCourse.forEach((e)->{
                            System.out.println("[ID:"+e.getCourseId()+"] [Name:"+e.getCourse()+"] [TeacherNum:"+e.getTeacherNum()+"] [AssistantNum:"+e.getAssistantNum()+"] [StudentNum:"+e.getStuNum()+"]");
                        });
                    }
                    else System.out.println("course not exist");
                }
            }
        }
        else System.out.println("not logged in");
    }
}
