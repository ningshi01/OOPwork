package Command.ForCourse;

import Command.Isgood;
import aggregate.*;
import aggregate.Course;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static aggregate.Users.user0;
import static aggregate.Course.course0;
public class addCourse {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId) {
        String patwrong="addCourse\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*";
        String pat="addCourse\\s+(\\S+)\\s+(\\S+)\\s*";
        Pattern r_wrong = Pattern.compile(patwrong);
        Pattern r = Pattern.compile(pat);
        Matcher m_wrong = r_wrong.matcher(command);
        Matcher m = r.matcher(command);
        if(m_wrong.find()) System.out.println("arguments illegal");
        else if(m.find()) {
            if(Users.loginFlag){
                if(user0.getType()==1)/*老师端*/{
                    if(Isgood.IsbadcourseId(m.group(1)))
                        System.out.println("course id illegal");
                    else if(true){
                        if(user0.MyCourseId.lastIndexOf(m.group(1))==-1){
                            if (!Isgood.Isbadcourse(m.group(2))){
                                course0 = new Course(m.group(1), m.group(2));
                                course0.teacherList.add(user0.getId());
                                course0.adminList.add(arr_User.get(arr_UsersId.lastIndexOf(user0.getId())));
                                course0.adminIdList.add(user0.getId());
                                course0.setTeacherNum();
                                int num = arr_courseIds.lastIndexOf(m.group(1));
                                if (num == -1) {
                                    arr_courses.add(course0);
                                    arr_courseIds.add(course0.getCourseId());
                                } else {
                                    arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()), course0);//course0写入总表
                                }
                                user0.MyCourse.add(course0);
                                user0.MyCourseId.add(m.group(1));
                                arr_User.set(arr_UsersId.lastIndexOf(user0.getId()), user0);//user0写入总表
                                System.out.println("add course success");
                            }else System.out.println("course name illegal");
                        } else System.out.println("course id duplication");
                    }
                } else System.out.println("permission denied");
            } else System.out.println("not logged in");
        } else System.out.println("arguments illegal");

    }
}
