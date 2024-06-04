package Command.ForAdmin;

import Command.Isgood;
import aggregate.Course;
import aggregate.Users;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Users.user0;
import static aggregate.Course.course0;

public class removeAdmin {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds, ArrayList<Users> arr_User, ArrayList<String> arr_UsersId){
        String pat="\\S+";
        String wpat="removeAdmin\\s+(\\S+)\\s+(\\S+)\\s*";
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
            String Id = m.group();
            if (Users.loginFlag) {
                if (user0.getType() == 1) {
                    if(Users.selectFlag){
                        if (!Isgood.IsBadId(Id)) {
                            int num1 = course0.teacherList.lastIndexOf(Id);
                            int num2 = course0.assistantList.lastIndexOf(Id);
                            if (num1 != -1 || num2 != -1) {
                                if (num1 != -1) {
                                    course0.adminList.remove(course0.adminIdList.lastIndexOf(m.group()));
                                    course0.adminIdList.remove(m.group());
                                    arr_User.get(arr_UsersId.lastIndexOf(course0.teacherList.get(num1))).helpCourse.remove(course0);
                                    course0.teacherList.remove(num1);
                                    course0.setTeacherNum();
                                    arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                                }
                                else {
                                    course0.adminList.remove(course0.adminIdList.lastIndexOf(m.group()));
                                    course0.adminIdList.remove(m.group());
                                    arr_User.get(arr_UsersId.lastIndexOf(course0.assistantList.get(num2))).helpCourse.remove(course0);
                                    arr_User.get(arr_UsersId.lastIndexOf(course0.assistantList.get(num2))).setStu_type();
                                    course0.assistantList.remove(num2);
                                    course0.setAssistantNum();
                                    arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                                }
                                System.out.println("remove admin success");
                            } else System.out.println("user id not exist");
                        } else System.out.println("user id illegal");
                    }else System.out.println("no course selected");
                } else System.out.println("permission denied");
            } else System.out.println("not logged in");
        }else System.out.println("arguments illegal");
    }
}
