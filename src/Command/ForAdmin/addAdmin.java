package Command.ForAdmin;

import Command.Isgood;
import aggregate.Course;
import aggregate.Users;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aggregate.Users.user0;
import static aggregate.Course.course0;
public class addAdmin {
    public static void run(String command, ArrayList<Course> arr_courses, ArrayList<String> arr_courseIds,ArrayList<Users> arr_User,ArrayList<String> arr_UsersId){
        String pat="\\S+";
        Pattern r= Pattern.compile(pat);
        Pattern rr=Pattern.compile(pat);
        Matcher m=r.matcher(command);
        Matcher mm= rr.matcher(command);
        Matcher xx= r.matcher(command);
        mm.find();
        if(!mm.find()) {
            System.out.println("arguments illegal");
            return;
        }
        int flag= 0;
        m.find();
        xx.find();
        if(Users.loginFlag){
            if(user0.getType()==1){
                if(Users.selectFlag){
                    while(m.find()){
                        if(Isgood.IsBadId(m.group())) {
                            flag=1;
                            break;
                        }
                        else if((m.group().length()!=5)&&(arr_UsersId.lastIndexOf(m.group())==-1)) {
                            flag = 2;
                            break;
                        }
                        else if((m.group().length()==5)&&(arr_UsersId.lastIndexOf(m.group())==-1)){
                            flag = 2;
                            break;
                        }
                    }
                    if(flag==1) System.out.println("user id illegal");
                    else if(flag==2) System.out.println("user id not exist");
                    else {
                        while(xx.find()){
                            if(xx.group().length()==5)/*添加老师*/{
                                if(course0.teacherList.lastIndexOf(xx.group())!=-1)
                                    continue;
                                course0.adminList.add(arr_User.get(arr_UsersId.lastIndexOf(xx.group())));
                                course0.adminIdList.add(xx.group());
                                course0.teacherList.add(xx.group());
                                course0.setTeacherNum();
                                arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                                //user的改动写入总表
                                arr_User.get(arr_UsersId.lastIndexOf(xx.group())).MyCourse.add(course0);
                                arr_User.get(arr_UsersId.lastIndexOf(xx.group())).MyCourseId.add(xx.group());
                            }
                            else /*添加助教*/{
                                if(course0.assistantList.lastIndexOf(xx.group())!=-1)
                                    continue;
                                course0.adminList.add(arr_User.get(arr_UsersId.lastIndexOf(xx.group())));
                                course0.adminIdList.add(xx.group());
                                course0.assistantList.add(xx.group());
                                course0.setAssistantNum();
                                arr_courses.set(arr_courseIds.lastIndexOf(course0.getCourseId()),course0);//course0写入总表
                                //user的改动写入总表
                                arr_User.get(arr_UsersId.lastIndexOf(xx.group())).helpCourseId.add(course0.getCourseId());
                                arr_User.get(arr_UsersId.lastIndexOf(xx.group())).helpCourse.add(course0);
                                arr_User.get(arr_UsersId.lastIndexOf(xx.group())).setStu_type();
                            }
                        }
                        System.out.println("add admin success");
                    }
                } else System.out.println("no course selected");
            } else System.out.println("permission denied");
        } else System.out.println("not logged in");
    }
}
